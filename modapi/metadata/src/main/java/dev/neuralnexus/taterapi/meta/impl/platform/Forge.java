/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.meta.impl.platform;

import dev.neuralnexus.taterapi.meta.Platform;

public interface Forge {
    Platform FORGE =
            new PlatformImpl(
                    "Forge",
                    "net.minecraftforge.fml.loading.FMLLoader",
                    "net.minecraftforge.fml.common.Loader",
                    "cpw.mods.fml.common.Loader");
    Platform GOLDENFORGE =
            new PlatformImpl("GoldenForge", "org.goldenforgelauncher.GoldenForgeEntryPoint");
    Platform NEOFORGE = new PlatformImpl("NeoForge", "net.neoforged.neoforge.common.NeoForge");

    // Forge+Bukkit Hybrids
    Platform MCPCPLUSPLUS = new PlatformImpl("MCPC++", "not.defined"); // TODO: Find a MCPC++ class
    Platform CAULDRON = new PlatformImpl("Cauldron", "net.minecraftforge.cauldron.CauldronConfig");
    Platform KCAULDRON =
            new PlatformImpl("KCauldron", "net.minecraftforge.kcauldron.KCauldronConfig");
    Platform THERMOS = new PlatformImpl("Thermos", "thermos.ThermosConfig");
    Platform CRUCIBLE = new PlatformImpl("Crucible", "io.github.crucible.CrucibleConfig");
    Platform MOHIST =
            new PlatformImpl("Mohist", "com.mohistmc.MohistMC", "com.mohistmc.MohistMCStart");
    Platform MAGMA =
            new PlatformImpl(
                    "Magma",
                    "org.magmafoundation.magma.Magma",
                    "org.magmafoundation.magma.MagmaStart");
    Platform KETTING = new PlatformImpl("Ketting", "org.kettingpowered.ketting.core.Ketting");

    // Forge+NeoForge Hybrids
    Platform YOUER = new PlatformImpl("Youer"); // TODO: Update when released
}
