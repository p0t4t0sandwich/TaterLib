/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.metadata.impl;

import static dev.neuralnexus.taterapi.util.ReflectionUtil.checkForClass;

import dev.neuralnexus.taterapi.MinecraftVersion;
import dev.neuralnexus.taterapi.Platform;
import dev.neuralnexus.taterapi.logger.Logger;
import dev.neuralnexus.taterapi.metadata.ModInfo;
import dev.neuralnexus.taterapi.metadata.PlatformData;

import java.nio.file.Path;
import java.util.List;

public class PlatformDataImpl implements PlatformData {
    private static PlatformData INSTANCE;
    private static MinecraftVersion MC_VERSION = MinecraftVersion.UNKNOWN;
    private static String MOD_LOADER_VERSION = "Unknown";

    public static PlatformData getInstance() {
        if (INSTANCE == null) {
            Platform platform = Platform.get();
            if (platform.isBungeeCordBased()) {
                INSTANCE = new BungeeCordData();
            } else if (platform.isFabricBased()) {
                INSTANCE = new FabricData();
            } else if (platform.isNeoForgeBased()) {
                INSTANCE = new NeoForgeData();
            } else if (platform.isForgeBased()) {
                INSTANCE = ForgeData.create();
            } else if (platform.isBukkitBased()) {
                INSTANCE = new BukkitData();
            } else if (platform.isSpongeBased()) {
                INSTANCE = SpongeData.create();
            } else if (platform.isVelocityBased()) {
                INSTANCE = new VelocityData();
            } else if (checkForClass("org.spongepowered.asm.service.MixinService")) {
                INSTANCE = new VanillaData();
            }
        }
        return INSTANCE;
    }

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
