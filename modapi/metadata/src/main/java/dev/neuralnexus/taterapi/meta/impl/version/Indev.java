/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.meta.impl.version;

import dev.neuralnexus.taterapi.meta.MinecraftVersion;

// 3
@SuppressWarnings("unused")
public interface Indev {
    MinecraftVersion Indev_0_31 = MinecraftVersionImpl.of("Indev 0.31");
    MinecraftVersion Indev = MinecraftVersionImpl.of("Indev");
}
