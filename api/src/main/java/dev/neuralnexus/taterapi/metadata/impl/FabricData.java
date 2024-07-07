/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterapi.metadata.impl;

import dev.neuralnexus.taterapi.MinecraftVersion;
import dev.neuralnexus.taterapi.metadata.ModInfo;
import dev.neuralnexus.taterapi.metadata.impl.fabric.FabricLogger_18_21;
import dev.neuralnexus.taterapi.metadata.impl.fabric.FabricLogger_7_17;
import dev.neuralnexus.taterapi.logger.Logger;
import dev.neuralnexus.taterapi.metadata.PlatformData;

import net.fabricmc.loader.api.FabricLoader;

import java.util.List;
import java.util.stream.Collectors;

/** Stores data about the Fabric platform */
public class FabricData implements PlatformData {
    @Override
    public MinecraftVersion minecraftVersion() {
        return MinecraftVersion.from(
                FabricLoader.getInstance()
                        .getModContainer("minecraft")
                        .get()
                        .getMetadata()
                        .getVersion()
                        .getFriendlyString());
    }

    @Override
    public String modLoaderVersion() {
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
    public List<ModInfo> modList() {
        return FabricLoader.getInstance().getAllMods().stream()
                .map(
                        modContainer ->
                                new ModInfo(
                                        modContainer.getMetadata().getId(),
                                        modContainer.getMetadata().getName(),
                                        modContainer
                                                .getMetadata()
                                                .getVersion()
                                                .getFriendlyString()))
                .collect(Collectors.toList());
    }

    @Override
    public Logger logger(String pluginId) {
        MinecraftVersion version = minecraftVersion();
        if (version.isOlderThan(MinecraftVersion.V1_18)) {
            return FabricLogger_7_17.logger(pluginId);
        }
        return FabricLogger_18_21.logger(pluginId);
    }
}
