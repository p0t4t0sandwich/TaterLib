/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.testmod;

import dev.neuralnexus.modapi.metadata.Logger;
import dev.neuralnexus.modapi.metadata.MetaAPI;
import dev.neuralnexus.taterapi.event.api.CommandEvents;
import dev.neuralnexus.taterlib.testmod.api.TestModAPI;
import dev.neuralnexus.taterlib.testmod.api.TestModAPIProvider;
import dev.neuralnexus.taterlib.testmod.listeners.CommandListener;
import dev.neuralnexus.taterloader.event.api.PluginEvents;
import dev.neuralnexus.taterloader.plugin.Plugin;

/** Main class for the plugin. */
public class TestMod implements Plugin {
    public static final String PROJECT_NAME = "Example";
    public static final String PROJECT_ID = "example";
    public static final String PROJECT_VERSION = "0.1.0-R0.1-SNAPSHOT";
    public static final String PROJECT_AUTHORS = "yournamehere";
    public static final String PROJECT_DESCRIPTION = "A cross-API plugin that uses TaterLib!";
    public static final String PROJECT_URL = "https://github.com/yournamehere/Example";

    private static final TestMod instance = new TestMod();
    private static final Logger logger = Logger.create(TestMod.PROJECT_ID);
    private static boolean RELOADED = false;

    private TestMod() {}

    /**
     * Getter for the singleton instance of the class.
     *
     * @return The singleton instance
     */
    public static TestMod instance() {
        return instance;
    }

    /**
     * Get the logger
     *
     * @return The logger
     */
    public static Logger logger() {
        return logger;
    }

    /**
     * Get if the plugin has reloaded
     *
     * @return If the plugin has reloaded
     */
    public static boolean hasReloaded() {
        return RELOADED;
    }

    @Override
    public String name() {
        return TestMod.PROJECT_NAME;
    }

    @Override
    public String id() {
        return TestMod.PROJECT_ID;
    }

    @Override
    public void onEnable() {
        MetaAPI api = MetaAPI.instance();

        logger.info(
                TestMod.PROJECT_NAME
                        + " is running on "
                        + api.platform()
                        + " "
                        + api.version()
                        + "!");
        PluginEvents.DISABLED.register(event -> onDisable());

        // Config

        if (!RELOADED) {
            // Register listeners
            // CommandEvents.REGISTER_COMMAND.register(CommandListener::onRegisterCommand);
            CommandEvents.REGISTER_BRIGADIER_COMMAND.register(
                    CommandListener::onRegisterBrigadierCommand);
        }

        TestModAPIProvider.register(new TestModAPI("someData"));

        logger.info(PROJECT_NAME + " has been started!");
    }

    @Override
    public void onDisable() {
        // Remove references to objects

        // Unregister API
        TestModAPIProvider.unregister();

        logger.info(PROJECT_NAME + " has been stopped!");
    }

    /** Reload */
    public void reload() {
        RELOADED = true;
        onDisable();
        onEnable();
        logger.info(PROJECT_NAME + " has been reloaded!");
    }
}
