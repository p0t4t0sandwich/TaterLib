/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib;

import dev.neuralnexus.modapi.metadata.MetaAPI;
import dev.neuralnexus.taterapi.event.api.PluginEvents;
import dev.neuralnexus.taterapi.loader.impl.LoaderImpl;
import dev.neuralnexus.taterapi.loader.plugin.Plugin;

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
    default void onEnable() {
        TaterLib.logger()
                .info(
                        LoaderImpl.PROJECT_NAME
                                + " is running on "
                                + MetaAPI.instance().platform().asString()
                                + " "
                                + MetaAPI.instance().version().asString()
                                + "!");
        TaterLib.start();
    }

    @Override
    default void onDisable() {
        TaterLib.stop();
        TaterLib.logger().info(LoaderImpl.PROJECT_NAME + " has been disabled!");
    }
}
