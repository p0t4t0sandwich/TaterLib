package dev.neuralnexus.taterlib;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.event.api.PluginEvents;
import dev.neuralnexus.taterlib.event.plugin.CommonPluginDisableEvent;
import dev.neuralnexus.taterlib.logger.AbstractLogger;
import dev.neuralnexus.taterlib.plugin.Plugin;

/** General TaterLib plugin interface. */
public interface TaterLibPlugin extends Plugin {
    @Override
    default String getName() {
        return TaterLib.Constants.PROJECT_NAME;
    }

    @Override
    default String getId() {
        return TaterLib.Constants.PROJECT_ID;
    }

    @Override
    default void pluginStart(Object plugin, AbstractLogger logger) {
        logger.info(
                TaterLib.Constants.PROJECT_NAME
                        + " is running on "
                        + TaterAPIProvider.serverType()
                        + " "
                        + TaterAPIProvider.minecraftVersion()
                        + "!");
        TaterLib.start(plugin, logger);
    }

    @Override
    default void pluginStop() {
        TaterLib.stop();
        TaterLib.getLogger().info(TaterLib.Constants.PROJECT_NAME + " has been disabled!");
        PluginEvents.DISABLED.invoke(new CommonPluginDisableEvent());
    }
}
