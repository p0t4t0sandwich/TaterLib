/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.meta.impl.platform;

import dev.neuralnexus.taterapi.meta.Platform;

public interface Bukkit {
    Platform BUKKIT =
            new PlatformImpl("Bukkit", "org.bukkit.Bukkit", "org.bukkit.craftbukkit.Main");
    Platform POSEIDON = new PlatformImpl("Poseidon", "com.legacyminecraft.poseidon.PoseidonConfig");
    Platform SPIGOT = new PlatformImpl("Spigot", "org.spigotmc.CustomTimingsHandler");
    Platform PAPER =
            new PlatformImpl(
                    "Paper",
                    "com.destroystokyo.paper.PaperConfig",
                    "io.papermc.paperclip.Paperclip");
    Platform FOLIA = new PlatformImpl("Folia", "io.papermc.paper.threadedregions.RegionizedServer");
    Platform PUFFERFISH =
            new PlatformImpl("Pufferfish", "gg.pufferfish.pufferfish.PufferfishConfig");
    Platform PURPUR = new PlatformImpl("Purpur", "org.purpurmc.purpur.PurpurWorldConfig");
}
