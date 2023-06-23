package dev.neuralnexus.taterapi.common;

import dev.neuralnexus.taterapi.common.api.TaterAPIProvider;
import dev.neuralnexus.taterapi.common.player.cache.TaterPlayerCache;
import dev.neuralnexus.taterapi.common.relay.MessageRelay;
import dev.neuralnexus.taterapi.common.storage.DataSource;
import dev.neuralnexus.taterapi.common.storage.Database;
import dev.dejvokep.boostedyaml.YamlDocument;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static dev.neuralnexus.taterapi.common.Utils.runTaskAsync;

public class TaterAPI {
    /**
     * Properties of the TaterAPI class.
     * config: The config file
     * logger: The logger
     * singleton: The singleton instance of the TaterAPI class
     * STARTED: Whether the PanelServerManager has been started
     */
    private static YamlDocument config;
    private static Object logger;
    private static TaterAPI singleton = null;
    private static boolean STARTED = false;
    public static boolean cancelChat = false;
    private static MessageRelay messageRelay;

    /**
     * Constructor for the TaterAPI class.
     * @param configPath The path to the config file
     * @param logger The logger
     */
    public TaterAPI(String configPath, Object logger) {
        singleton = this;
        TaterAPI.logger = logger;

        // Config
        try {
            config = YamlDocument.create(new File("." + File.separator + configPath + File.separator + "TaterAPI", "config.yml"),
                    Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("config.yml"))
            );
            config.reload();
        } catch (IOException | NullPointerException e) {
            useLogger("Failed to load config.yml!\n" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Getter for the singleton instance of the PanelServerManager class.
     * @return The singleton instance
     */
    public static TaterAPI getInstance() {
        return singleton;
    }

    /**
     * Use whatever logger is being used.
     * @param message The message to log
     */
    public static void useLogger(String message) {
        if (logger instanceof java.util.logging.Logger) {
            ((java.util.logging.Logger) logger).info(message);
        } else if (logger instanceof org.slf4j.Logger) {
            ((org.slf4j.Logger) logger).info(message);
        } else {
            System.out.println(message);
        }
    }

    /**
     * Start TaterAPI
     */
    public static void start() {
        if (STARTED) {
            useLogger("TaterAPI has already started!");
            return;
        }
        STARTED = true;

        useLogger("TaterAPI has been started!");
        TaterAPIProvider.register(singleton);
    }

    /**
     * Stop TaterAPI
     */
    public static void stop() {
        if (!STARTED) {
            useLogger("TaterAPI has already stopped!");
            return;
        }
        STARTED = false;

        useLogger("TaterAPI has been stopped!");
        TaterAPIProvider.unregister();
    }

    /**
     * Get the server name from the config
     * @return The server name
     */
    public static String getServerName() {
        return config.getString("server.name");
    }

    /**
     * Get the TaterAPI version from the config
     * @return The TaterAPI version
     */
    public static String getVersion() {
        return config.getString("apiversion");
    }

    /**
     * Get the MessageRelay instance
     */
    public static MessageRelay getMessageRelay() {
        return messageRelay;
    }

    /**
     * Set the MessageRelay instance
     * @param messageRelay The MessageRelay instance
     */
    public static void setMessageRelay(MessageRelay messageRelay) {
        TaterAPI.messageRelay = messageRelay;
    }
}
