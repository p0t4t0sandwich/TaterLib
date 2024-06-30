/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.event.api.PluginEvents;
import dev.neuralnexus.taterlib.event.plugin.CommonPluginDisableEvent;
import dev.neuralnexus.taterlib.plugin.Plugin;

/** General TaterLib plugin interface. */
public interface TaterLibPlugin extends Plugin {
    @Override
    default String name() {
        return TaterLib.Constants.PROJECT_NAME;
    }

    @Override
    default String id() {
        return TaterLib.Constants.PROJECT_ID;
    }

    @Override
    default void start(Object plugin, Object server) {
        logger().info(
                        TaterLib.Constants.PROJECT_NAME
                                + " is running on "
                                + TaterAPIProvider.platform()
                                + " "
                                + TaterAPIProvider.minecraftVersion()
                                + "!");
        TaterLib.start(plugin, server);
    }

    @Override
    default void stop() {
        TaterLib.stop();
        logger().info(TaterLib.Constants.PROJECT_NAME + " has been disabled!");
        PluginEvents.DISABLED.invoke(new CommonPluginDisableEvent());
    }
}
