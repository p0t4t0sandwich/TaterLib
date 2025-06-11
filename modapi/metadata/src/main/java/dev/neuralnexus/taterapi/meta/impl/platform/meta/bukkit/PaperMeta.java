/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.meta.impl.platform.meta.bukkit;

import org.bukkit.Bukkit;

/** Wrapper for Paper's getMinecraftVersion method */
final class PaperMeta {
    public static String getMinecraftVersion() {
        return Bukkit.getMinecraftVersion();
    }
}
