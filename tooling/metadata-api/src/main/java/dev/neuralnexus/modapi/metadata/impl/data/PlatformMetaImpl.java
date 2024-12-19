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
import dev.neuralnexus.modapi.metadata.Platform;
import dev.neuralnexus.modapi.metadata.Platforms;
import dev.neuralnexus.modapi.metadata.impl.platform.meta.BukkitMeta;
import dev.neuralnexus.modapi.metadata.impl.platform.meta.BungeeCordMeta;
import dev.neuralnexus.modapi.metadata.impl.platform.meta.FabricMeta;
import dev.neuralnexus.modapi.metadata.impl.platform.meta.NeoForgeMeta;
import dev.neuralnexus.modapi.metadata.impl.platform.meta.VanillaMeta;
import dev.neuralnexus.modapi.metadata.impl.platform.meta.VelocityMeta;
import dev.neuralnexus.modapi.metadata.impl.platform.meta.forge.ForgeData;
import dev.neuralnexus.modapi.metadata.impl.platform.meta.sponge.SpongeData;

import java.nio.file.Path;
import java.util.List;

public final class PlatformMetaImpl implements Platform.Meta {
    private static Platform.Meta INSTANCE;
    private static MinecraftVersion MC_VERSION = MinecraftVersion.UNKNOWN;
    private static String MOD_LOADER_VERSION = "Unknown";

    public static Platform.Meta getInstance() {
        if (INSTANCE == null) {
            if (Platforms.isBungeeCord()) {
                INSTANCE = new BungeeCordMeta();
            } else if (Platforms.isFabric()) {
                INSTANCE = new FabricMeta();
            } else if (Platforms.isNeoForge()) {
                INSTANCE = new NeoForgeMeta();
            } else if (Platforms.isForge()) {
                INSTANCE = ForgeData.create();
            } else if (Platforms.isBukkit()) {
                INSTANCE = new BukkitMeta();
            } else if (Platforms.isSponge()) {
                INSTANCE = SpongeData.create();
            } else if (Platforms.isVelocity()) {
                INSTANCE = new VelocityMeta();
            } else if (checkForClass("org.spongepowered.asm.service.MixinService")) {
                INSTANCE = new VanillaMeta();
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
    public String loaderVersion() {
        if (MOD_LOADER_VERSION.equals("Unknown")) {
            MOD_LOADER_VERSION = INSTANCE.loaderVersion();
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
