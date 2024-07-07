/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterapi.metadata.impl;

import dev.neuralnexus.taterapi.MinecraftVersion;
import dev.neuralnexus.taterapi.metadata.ModInfo;
import dev.neuralnexus.taterapi.logger.Logger;
import dev.neuralnexus.taterapi.logger.impl.Slf4jLogger;
import dev.neuralnexus.taterapi.metadata.PlatformData;

import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;

import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/** Stores data about the NeoForge platform */
public class NeoForgeData implements PlatformData {
    @Override
    public MinecraftVersion minecraftVersion() {
        return MinecraftVersion.from(FMLLoader.versionInfo().mcVersion());
    }

    @Override
    public String modLoaderVersion() {
        return FMLLoader.versionInfo().neoForgeVersion();
    }

    @Override
    public List<ModInfo> modList() {
        return ModList.get().getMods().stream()
                .map(
                        modContainer ->
                                new ModInfo(
                                        modContainer.getModId(),
                                        modContainer.getDisplayName(),
                                        modContainer.getVersion().toString()))
                .collect(Collectors.toList());
    }

    @Override
    public Logger logger(String pluginId) {
        return new Slf4jLogger(LoggerFactory.getLogger(pluginId));
    }
}
