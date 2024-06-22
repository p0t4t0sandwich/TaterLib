/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.loader.impl.metadata;

import static dev.neuralnexus.taterlib.utils.ReflectionUtil.checkForClass;
import static dev.neuralnexus.taterlib.utils.ReflectionUtil.checkForMethod;

import dev.neuralnexus.taterlib.loader.api.MinecraftVersion;
import dev.neuralnexus.taterlib.loader.api.ModInfo;
import dev.neuralnexus.taterlib.loader.api.Platform;
import dev.neuralnexus.taterlib.loader.api.PlatformData;
import dev.neuralnexus.taterlib.loader.impl.metadata.forge.CPWLoaderData;
import dev.neuralnexus.taterlib.loader.impl.metadata.forge.FMLLoaderData;
import dev.neuralnexus.taterlib.loader.impl.metadata.forge.MCFLoaderData;

import java.nio.file.Path;
import java.util.List;

public class PlatformDataImpl implements PlatformData {
    private static PlatformData INSTANCE;
    private static MinecraftVersion MC_VERSION = MinecraftVersion.UNKNOWN;
    private static String MOD_LOADER_VERSION = "Unknown";

    public PlatformDataImpl() {
        Platform platform = Platform.platform();
        if (platform.isBungeeCordBased()) {
            INSTANCE = new BungeeCordData();
        } else if (platform.isFabricBased()) {
            INSTANCE = new FabricData();
        } else if (platform.isNeoForgeBased()) {
            INSTANCE = new NeoForgeData();
        } else if (platform.isForgeBased()) {
            if (checkForClass("net.minecraftforge.fml.loading.FMLLoader")) {
                INSTANCE = new FMLLoaderData();
            } else if (checkForClass("net.minecraftforge.fml.common.Loader")) {
                INSTANCE = new MCFLoaderData();
            } else if (checkForClass("cpw.mods.fml.common.Loader")) {
                INSTANCE = new CPWLoaderData();
            }
        } else if (platform.isBukkitBased()) {
            INSTANCE = new BukkitData();
        } else if (platform.isSpongeBased()) {
            if (checkForMethod("org.spongepowered.api.Sponge", "platform")) {
                INSTANCE = new SpongeData();
            } else if (checkForMethod("org.spongepowered.api.Sponge", "getPlatform")) {
                INSTANCE = new SpongeData();
            }
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
