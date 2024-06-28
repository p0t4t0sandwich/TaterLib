/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca The project is Licensed under <a
 * href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a> The API is Licensed
 * under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.api.impl.metadata;

import static dev.neuralnexus.taterlib.utils.ReflectionUtil.checkForClass;

import dev.neuralnexus.taterlib.api.MinecraftVersion;
import dev.neuralnexus.taterlib.api.ModInfo;
import dev.neuralnexus.taterlib.api.Platform;
import dev.neuralnexus.taterlib.api.PlatformData;

import java.nio.file.Path;
import java.util.List;

public class PlatformDataImpl implements PlatformData {
    private static PlatformData INSTANCE;
    private static MinecraftVersion MC_VERSION = MinecraftVersion.UNKNOWN;
    private static String MOD_LOADER_VERSION = "Unknown";

    public PlatformDataImpl() {
        Platform platform = Platform.get();
        if (platform.isBungeeCordBased()) {
            INSTANCE = new BungeeCordData();
        } else if (platform.isFabricBased()) {
            INSTANCE = new FabricData();
        } else if (platform.isNeoForgeBased()) {
            INSTANCE = new NeoForgeData();
        } else if (platform.isForgeBased()) {
            INSTANCE = new ForgeData();
        } else if (platform.isBukkitBased()) {
            INSTANCE = new BukkitData();
        } else if (platform.isSpongeBased()) {
            INSTANCE = new SpongeData();
        } else if (platform.isVelocityBased()) {
            INSTANCE = new VelocityData();
        }
        if (checkForClass("org.spongepowered.asm.service.MixinService")) {
            INSTANCE = new VanillaData();
        }
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
    public Path modFolder() {
        return INSTANCE.modFolder();
    }

    @Override
    public Path configFolder() {
        return INSTANCE.configFolder();
    }
}
