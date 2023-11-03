package dev.neuralnexus.taterlib.common;

import dev.neuralnexus.taterlib.common.event.api.CommandEvents;
import dev.neuralnexus.taterlib.common.listeners.command.CommandListener;
import dev.neuralnexus.taterlib.common.logger.AbstractLogger;
import dev.neuralnexus.taterlib.common.api.TaterLibAPIProvider;
import dev.neuralnexus.taterlib.common.event.api.PlayerEvents;
import dev.neuralnexus.taterlib.common.listeners.player.PlayerListener;
import dev.neuralnexus.taterlib.common.relay.MessageRelay;

import java.util.*;
import java.util.function.Consumer;

public class TaterLib {
    private static final TaterLib instance = new TaterLib();
    public static AbstractLogger logger;
    private static String configPath;
    private static boolean STARTED = false;
    private static boolean RELOADED = false;
    private static MessageRelay messageRelay;
    private static final HashMap<String, Object> hooks = new HashMap<>();
    public static Consumer<Set<String>> registerChannels = (channels) -> {};

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

        if (STARTED) {
            logger.info("TaterLib has already started!");
            return;
        }
        STARTED = true;

        if (!RELOADED) {
            // Register player listeners
            PlayerEvents.LOGIN.register(PlayerListener::onPlayerLogin);
            PlayerEvents.LOGOUT.register(PlayerListener::onPlayerLogout);
            PlayerEvents.SERVER_SWITCH.register(PlayerListener::onServerSwitch);

            // Register brigadier commands
            CommandEvents.REGISTER_BRIGADIER_COMMAND.register(CommandListener::onRegisterBrigadierCommand);
        }

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
     * Reload
     */
    public static void reload() {
        if (!STARTED) {
            logger.info("TaterLib has not been started!");
            return;
        }
        RELOADED = true;

        // Stop
        stop();

        // Start
        start(configPath, logger);

        logger.info("TaterLib has been reloaded!");
    }

    /**
     * Get the current version of TaterLib
     * @return The current version of TaterLib
     */
    public static String getVersion() {
        return "1.1.0-R0.1-SNAPSHOT";
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
     * Add a hook to the hooks map
     * @param hookName The name of the hook
     * @param hook The hook to add
     */
     public static void addHook(String hookName, Object hook) {
        hooks.put(hookName, hook);
     }

    /**
     * Get if a hook exists
     * @param hookName The name of the hook
     */
    public static boolean isHooked(String hookName) {
        return hooks.containsKey(hookName);
    }

    /**
     * Set the registerChannels consumer
     * @param registerChannels The registerChannels consumer
     */
    public static void setRegisterChannels(Consumer<Set<String>> registerChannels) {
        TaterLib.registerChannels = registerChannels;
    }
}
