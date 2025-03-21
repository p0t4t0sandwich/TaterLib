/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib;

import dev.neuralnexus.taterapi.impl.loader.LoaderImpl;
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
        TaterLib.start();
    }

    @Override
    default void onDisable() {
        TaterLib.stop();
    }
}
