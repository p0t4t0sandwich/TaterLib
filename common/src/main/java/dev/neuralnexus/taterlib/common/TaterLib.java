package dev.neuralnexus.taterlib.common;

import dev.neuralnexus.taterlib.common.api.TaterLibAPIProvider;
import dev.neuralnexus.taterlib.common.relay.MessageRelay;
import dev.dejvokep.boostedyaml.YamlDocument;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class TaterLib {
    /**
     * Properties of the TaterLib class.
     * instance: The singleton instance of the TaterLib class
     * config: The config file
     * logger: The logger
     * STARTED: Whether TaterLib has been started
     * cancelChat: Whether to cancel chat
     * messageRelay: The message relay
     * hooks: The hooks
     */
    private static final TaterLib instance = new TaterLib();
    private static YamlDocument config;
    private static Object logger;
    private static String configPath;
    private static boolean STARTED = false;
    public static boolean cancelChat = false;
    private static MessageRelay messageRelay;
    private static final ArrayList<Object> hooks = new ArrayList<>();

    /**
     * Constructor for the TaterLib class.
     */
    public TaterLib() {}

    /**
     * Getter for the singleton instance of the TaterLib class.
     * @return The singleton instance
     */
    public static TaterLib getInstance() {
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
     * Start TaterLib
     * @param configPath The path to the config file
     * @param logger The logger
     */
    public static void start(String configPath, Object logger) {
        TaterLib.configPath = configPath;
        TaterLib.logger = logger;

        // Config
        try {
            config = YamlDocument.create(new File("." + File.separator + configPath + File.separator + "TaterLib", "config.yml"),
                    Objects.requireNonNull(TaterLib.class.getClassLoader().getResourceAsStream("config.yml"))
            );
            config.reload();
        } catch (IOException | NullPointerException e) {
            useLogger("Failed to load config.yml!\n" + e.getMessage());
            e.printStackTrace();
        }

        if (STARTED) {
            useLogger("TaterLib has already started!");
            return;
        }
        STARTED = true;

        useLogger("TaterLib has been started!");
        TaterLibAPIProvider.register(instance);
    }

    /**
     * Start TaterLib
     */
    public static void start() {
        start(configPath, logger);
    }

    /**
     * Stop TaterLib
     */
    public static void stop() {
        if (!STARTED) {
            useLogger("TaterLib has already stopped!");
            return;
        }
        STARTED = false;

        useLogger("TaterLib has been stopped!");
        TaterLibAPIProvider.unregister();
    }

    /**
     * Get the TaterLib version from the config
     * @return The TaterLib version
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
        TaterLib.messageRelay = messageRelay;
    }

    /**
     * Add a hook to the hooks list
     * @param hook The hook to add
     */
     public static void addHook(Object hook) {
        hooks.add(hook);
     }
}
