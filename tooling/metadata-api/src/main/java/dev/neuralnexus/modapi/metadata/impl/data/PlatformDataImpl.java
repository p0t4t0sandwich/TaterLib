/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata.impl.data;

import static dev.neuralnexus.modapi.metadata.impl.util.ReflectionUtil.checkForClass;

import dev.neuralnexus.modapi.metadata.Logger;
import dev.neuralnexus.modapi.metadata.Mappings;
import dev.neuralnexus.modapi.metadata.MinecraftVersion;
import dev.neuralnexus.modapi.metadata.ModInfo;
import dev.neuralnexus.modapi.metadata.PlatformData;
import dev.neuralnexus.modapi.metadata.Platforms;
import dev.neuralnexus.modapi.metadata.impl.data.bukkit.BukkitData;
import dev.neuralnexus.modapi.metadata.impl.data.bungeecord.BungeeCordData;
import dev.neuralnexus.modapi.metadata.impl.data.fabric.FabricData;
import dev.neuralnexus.modapi.metadata.impl.data.forge.ForgeData;
import dev.neuralnexus.modapi.metadata.impl.data.neoforge.NeoForgeData;
import dev.neuralnexus.modapi.metadata.impl.data.sponge.SpongeData;
import dev.neuralnexus.modapi.metadata.impl.data.vanilla.VanillaData;
import dev.neuralnexus.modapi.metadata.impl.data.velocity.VelocityData;

import java.nio.file.Path;
import java.util.List;

public class PlatformDataImpl implements PlatformData {
    private static PlatformData INSTANCE;
    private static MinecraftVersion MC_VERSION = MinecraftVersion.UNKNOWN;
    private static String MOD_LOADER_VERSION = "Unknown";

    public static PlatformData getInstance() {
        if (INSTANCE == null) {
            if (Platforms.isBungeeCord()) {
                INSTANCE = new BungeeCordData();
            } else if (Platforms.isFabric()) {
                INSTANCE = new FabricData();
            } else if (Platforms.isNeoForge()) {
                INSTANCE = new NeoForgeData();
            } else if (Platforms.isForge()) {
                INSTANCE = ForgeData.create();
            } else if (Platforms.isBukkit()) {
                INSTANCE = new BukkitData();
            } else if (Platforms.isSponge()) {
                INSTANCE = SpongeData.create();
            } else if (Platforms.isVelocity()) {
                INSTANCE = new VelocityData();
            } else if (checkForClass("org.spongepowered.asm.service.MixinService")) {
                INSTANCE = new VanillaData();
            }
        }
        return INSTANCE;
    }

    //    public static PlatformData getInstance() {} // Create overload that specifies the platform

    @Override
    public MinecraftVersion minecraftVersion() {
        if (MC_VERSION == MinecraftVersion.UNKNOWN) {
            MC_VERSION = INSTANCE.minecraftVersion();
        }
        return MC_VERSION;
    }

    @Override
    public String modLoaderVersion() {
        if (MOD_LOADER_VERSION.equals("Unknown")) {
            MOD_LOADER_VERSION = INSTANCE.modLoaderVersion();
        }
        return MOD_LOADER_VERSION;
    }

    @Override
    public Mappings mappings() {
        return INSTANCE.mappings();
    }

    @Override
    public List<ModInfo> modList() {
        return INSTANCE.modList();
    }

    @Override
    public Logger logger(String pluginId) {
        return INSTANCE.logger(pluginId);
    }

    @Override
    public Path modFolder() {
        return INSTANCE.modFolder();
    }

    @Override
    public Path configFolder() {
        return INSTANCE.configFolder();
    }
}
