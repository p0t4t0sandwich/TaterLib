/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib;

import dev.neuralnexus.taterapi.impl.loader.TaterLoader;
import dev.neuralnexus.taterapi.loader.plugin.Plugin;

import org.jspecify.annotations.NonNull;

/** General TaterLib plugin interface. */
public interface TaterLibPlugin extends Plugin {
    @Override
    default @NonNull String id() {
        return TaterLoader.MOD_ID;
    }

    @Override
    default @NonNull String name() {
        return TaterLoader.MOD_NAME;
    }

    @Override
    default void onInit() {
        // TODO: Verify No-Op?
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
