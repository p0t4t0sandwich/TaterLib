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
import dev.neuralnexus.modapi.metadata.impl.logger.Slf4jLogger;

import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;

import java.util.List;
import java.util.stream.Collectors;

/** Stores data about the NeoForge platform */
public class NeoForgeMeta implements Platform.Meta {
    @Override
    public MinecraftVersion minecraftVersion() {
        return MinecraftVersion.of(FMLLoader.versionInfo().mcVersion());
    }

    @Override
    public String loaderVersion() {
        return FMLLoader.versionInfo().neoForgeVersion();
    }

    @Override
    public Mappings mappings() {
        if (minecraftVersion().isOlderThan(MinecraftVersions.V20_2)) {
            return Mappings.SEARGE;
        }
        return Mappings.MOJMAP;
    }

    @Override
    public List<ModInfo> modList() {
        return ModList.get().getMods().stream()
                .map(
                        modContainer ->
                                new ModInfoImpl(
                                        modContainer.getModId(),
                                        modContainer.getDisplayName(),
                                        modContainer.getVersion().toString(),
                                        Platforms.NEOFORGE))
                .collect(Collectors.toList());
    }

    @Override
    public Logger<org.slf4j.Logger> logger(String modId) {
        return new Slf4jLogger(modId);
    }
}
