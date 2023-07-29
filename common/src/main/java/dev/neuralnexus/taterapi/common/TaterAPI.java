package dev.neuralnexus.taterapi.common;

import dev.neuralnexus.taterapi.common.api.TaterAPIProvider;
import dev.neuralnexus.taterapi.common.player.cache.PlayerCache;
import dev.neuralnexus.taterapi.common.relay.MessageRelay;
import dev.neuralnexus.taterapi.common.storage.DataSource;
import dev.neuralnexus.taterapi.common.storage.Database;
import dev.dejvokep.boostedyaml.YamlDocument;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static dev.neuralnexus.taterapi.common.Utils.runTaskAsync;

public class TaterAPI {
    /**
     * Properties of the TaterAPI class.
     * instance: The singleton instance of the TaterAPI class
     * config: The config file
     * logger: The logger
     * STARTED: Whether the PanelServerManager has been started
     * cancelChat: Whether to cancel chat
     * messageRelay: The message relay
     * hooks: The hooks
     */
    private static final TaterAPI instance = new TaterAPI();
    private static YamlDocument config;
    private static Object logger;
    private static String configPath;
    private static boolean STARTED = false;
    public static boolean cancelChat = false;
    private static MessageRelay messageRelay;
    private static final ArrayList<Object> hooks = new ArrayList<>();

    /**
     * Constructor for the TaterAPI class.
     */
    public TaterAPI() {}

    /**
     * Getter for the singleton instance of the TaterAPI class.
     * @return The singleton instance
     */
    public static TaterAPI getInstance() {
        return instance;
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
     * @param configPath The path to the config file
     * @param logger The logger
     */
    public static void start(String configPath, Object logger) {
        TaterAPI.configPath = configPath;
        TaterAPI.logger = logger;

        // Config
        try {
            config = YamlDocument.create(new File("." + File.separator + configPath + File.separator + "TaterAPI", "config.yml"),
                    Objects.requireNonNull(TaterAPI.class.getClassLoader().getResourceAsStream("config.yml"))
            );
            config.reload();
        } catch (IOException | NullPointerException e) {
            useLogger("Failed to load config.yml!\n" + e.getMessage());
            e.printStackTrace();
        }

        if (STARTED) {
            useLogger("TaterAPI has already started!");
            return;
        }
        STARTED = true;

        useLogger("TaterAPI has been started!");
        TaterAPIProvider.register(instance);
    }

    /**
     * Start TaterAPI
     */
    public static void start() {
        start(configPath, logger);
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

    /**
     * Add a hook to the hooks list
     * @param hook The hook to add
     */
     public static void addHook(Object hook) {
        hooks.add(hook);
     }
}
