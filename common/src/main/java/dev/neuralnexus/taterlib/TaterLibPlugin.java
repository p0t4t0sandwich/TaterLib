/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib;

import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterapi.event.api.PluginEvents;
import dev.neuralnexus.taterapi.event.plugin.CommonPluginDisableEvent;
import dev.neuralnexus.taterloader.impl.LoaderImpl;
import dev.neuralnexus.taterloader.plugin.Plugin;

/** General TaterLib plugin interface. */
public interface TaterLibPlugin extends Plugin {
    @Override
    default String name() {
        return LoaderImpl.PROJECT_NAME;
    }

    @Override
    default String id() {
        return LoaderImpl.PROJECT_ID;
    }

    @Override
    default void start() {
        TaterLib.logger()
                .info(
                        LoaderImpl.PROJECT_NAME
                                + " is running on "
                                + TaterAPIProvider.platform()
                                + " "
                                + TaterAPIProvider.minecraftVersion()
                                + "!");
        TaterLib.start();
    }

    @Override
    default void stop() {
        TaterLib.stop();
        TaterLib.logger().info(LoaderImpl.PROJECT_NAME + " has been disabled!");
        PluginEvents.DISABLED.invoke(new CommonPluginDisableEvent());
    }
}
