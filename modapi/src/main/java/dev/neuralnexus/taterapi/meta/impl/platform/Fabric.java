/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.meta.impl.platform;

import dev.neuralnexus.taterapi.meta.Platform;

public interface Fabric {
    Platform FABRIC = new PlatformImpl("Fabric", "net.fabricmc.loader.api.FabricLoader");
    Platform QUILT = new PlatformImpl("Quilt", "net.quiltservertools.quilt.api.QuiltServer");

    // Fabric+Bukkit Hybrids
    Platform CARDBOARD = new PlatformImpl("Cardboard", "org.cardboardpowered.CardboardConfig");
    Platform BANNER = new PlatformImpl("Banner", "com.mohistmc.banner.BannerMCStart");
}
