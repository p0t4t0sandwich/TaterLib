/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.meta.impl.platform;

import dev.neuralnexus.taterapi.meta.Platform;

public interface Vanilla {
    Platform VANILLA = new PlatformImpl("Vanilla", "net.minecraft.server.MinecraftServer");
}
