/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata.impl.platform.meta;

import dev.neuralnexus.modapi.metadata.Logger;
import dev.neuralnexus.modapi.metadata.Mappings;
import dev.neuralnexus.modapi.metadata.MinecraftVersion;
import dev.neuralnexus.modapi.metadata.MinecraftVersions;
import dev.neuralnexus.modapi.metadata.ModInfo;
import dev.neuralnexus.modapi.metadata.Platform;
import dev.neuralnexus.modapi.metadata.Platforms;
import dev.neuralnexus.modapi.metadata.impl.ModInfoImpl;
import dev.neuralnexus.modapi.metadata.impl.logger.ApacheLogger;
import dev.neuralnexus.modapi.metadata.impl.logger.Slf4jLogger;

import net.fabricmc.loader.api.FabricLoader;

import java.util.List;
import java.util.stream.Collectors;

/** Stores data about the Fabric platform */
public class FabricMeta implements Platform.Meta {
    @Override
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public MinecraftVersion minecraftVersion() {
        return MinecraftVersion.of(
                FabricLoader.getInstance()
                        .getModContainer("minecraft")
                        .get()
                        .getMetadata()
                        .getVersion()
                        .getFriendlyString());
    }

    @Override
    public String loaderVersion() {
        return FabricLoader.getInstance()
                        .getModContainer("fabric-api-base")
                        .get()
                        .getMetadata()
                        .getVersion()
                        .getFriendlyString()
                + FabricLoader.getInstance()
                        .getModContainer("fabric-loader")
                        .get()
                        .getMetadata()
                        .getVersion()
                        .getFriendlyString();
    }

    @Override
    public Mappings mappings() {
        if (minecraftVersion().isOlderThan(MinecraftVersions.V14)) {
            return Mappings.LEGACYINTERMEDIARY;
        }
        return Mappings.INTERMEDIARY;
    }

    @Override
    public List<ModInfo> modList() {
        return FabricLoader.getInstance().getAllMods().stream()
                .map(
                        modContainer ->
                                new ModInfoImpl(
                                        modContainer.getMetadata().getId(),
                                        modContainer.getMetadata().getName(),
                                        modContainer.getMetadata().getVersion().getFriendlyString(),
                                        Platforms.FABRIC))
                .collect(Collectors.toList());
    }

    @Override
    public Logger<?> logger(String modId) {
        MinecraftVersion version = minecraftVersion();
        if (version.isOlderThan(MinecraftVersions.V18)) {
            return new ApacheLogger(modId);
        }
        return new Slf4jLogger(modId);
    }
}
