package dev.neuralnexus.taterlib.common;

import dev.neuralnexus.taterlib.common.logger.AbstractLogger;

public interface TaterLibPlugin extends Plugin {
    /**
     * Start the plugin.
     */
    default void pluginStart(Object plugin, AbstractLogger logger) {
        try {
            logger.info("TaterLib is running on " + TaterLib.serverType + " " + TaterLib.minecraftVersion + "!");
            TaterLib.start(plugin, logger);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Stop the plugin.
     */
    default void pluginStop() {
        try {
            TaterLib.stop();
            TaterLib.logger.info("TaterLib has been disabled!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
