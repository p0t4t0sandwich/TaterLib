package dev.neuralnexus.taterlib.common;

import dev.neuralnexus.taterlib.common.abstractions.logger.AbstractLogger;
import dev.neuralnexus.taterlib.common.api.TaterLibAPIProvider;
import dev.neuralnexus.taterlib.common.relay.MessageRelay;
import dev.dejvokep.boostedyaml.YamlDocument;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class TaterLib {
    private static final TaterLib instance = new TaterLib();
    private static YamlDocument config;
    public static AbstractLogger logger;
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
     * Start TaterLib
     * @param configPath The path to the config file
     */
    public static void start(String configPath, AbstractLogger logger) {
        TaterLib.configPath = configPath;
        TaterLib.logger = logger;

        // Config
        try {
            config = YamlDocument.create(new File("." + File.separator + configPath + File.separator + "TaterLib", "config.yml"),
                    Objects.requireNonNull(TaterLib.class.getClassLoader().getResourceAsStream("config.yml"))
            );
            config.reload();
        } catch (IOException | NullPointerException e) {
            logger.info("Failed to load config.yml!\n" + e.getMessage());
            e.printStackTrace();
        }

        if (STARTED) {
            logger.info("TaterLib has already started!");
            return;
        }
        STARTED = true;

        logger.info("TaterLib has been started!");
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
            logger.info("TaterLib has already stopped!");
            return;
        }
        STARTED = false;

        logger.info("TaterLib has been stopped!");
        TaterLibAPIProvider.unregister();
    }

    /**
     * Get the current version of TaterLib
     * @return The current version of TaterLib
     */
    public static String getVersion() {
        return "1.0.0";
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
