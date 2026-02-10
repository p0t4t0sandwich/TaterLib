/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package net.neoforged.fml.loading;

import net.neoforged.api.distmarker.Dist;

import org.jetbrains.annotations.Nullable;

import java.util.concurrent.atomic.AtomicReference;

/** Fake NeoForge class */
public class FMLLoader {
    private static AtomicReference<@Nullable FMLLoader> current;

    private static Dist dist;

    public static VersionInfo versionInfo() {
        return new VersionInfo();
    }

    public VersionInfo getVersionInfo() {
        return new VersionInfo();
    }

    public static Dist getDist() {
        return dist;
    }

    public static FMLLoader getCurrent() {
        @Nullable FMLLoader current = getCurrentOrNull();
        if (current == null) {
            throw new IllegalStateException("There is no current FML Loader");
        }
        return current;
    }

    @Nullable public static FMLLoader getCurrentOrNull() {
        return current.get();
    }
}
