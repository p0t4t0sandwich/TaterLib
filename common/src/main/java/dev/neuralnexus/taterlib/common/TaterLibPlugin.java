package dev.neuralnexus.taterlib.common;

public interface TaterLibPlugin extends TemplatePlugin {
    /**
     * Start the plugin.
     */
    default void pluginStart() {
        try {
            useLogger("TaterLib is running on " + getServerType() + " " + getServerVersion() + "!");

            // Start the TaterLib
            TaterLib.start(pluginConfigPath(), pluginLogger());

            // Register hooks
            registerHooks();

            // Register event listeners
            registerEventListeners();

            // Register commands
            registerCommands();

            useLogger("TaterLib has been enabled!");

        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }

    /**
     * Stop the plugin.
     */
    default void pluginStop() {
        try {
            TaterLib.stop();
            useLogger("TaterLib has been disabled!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
