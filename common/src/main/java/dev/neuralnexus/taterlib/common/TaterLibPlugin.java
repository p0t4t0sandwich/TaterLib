package dev.neuralnexus.taterlib.common;

import dev.neuralnexus.taterlib.common.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.common.api.info.ServerType;
import dev.neuralnexus.taterlib.common.event.api.PluginEvents;
import dev.neuralnexus.taterlib.common.event.plugin.CommonPluginDisableEvent;
import dev.neuralnexus.taterlib.common.logger.AbstractLogger;

public interface TaterLibPlugin extends Plugin {
    /**
     * Start the plugin.
     */
    default void pluginStart(Object plugin, AbstractLogger logger) {
        logger.info(TaterLib.Constants.PROJECT_NAME + " is running on " + TaterAPIProvider.get().serverType() + " " + TaterAPIProvider.get().minecraftVersion() + "!");
        TaterLib.start(plugin, logger);
    }

    /**
     * Stop the plugin.
     */
    default void pluginStop() {
        TaterLib.stop();
        TaterLib.getLogger().info(TaterLib.Constants.PROJECT_NAME + " has been disabled!");
        PluginEvents.DISABLED.invoke(new CommonPluginDisableEvent());
    }
}
