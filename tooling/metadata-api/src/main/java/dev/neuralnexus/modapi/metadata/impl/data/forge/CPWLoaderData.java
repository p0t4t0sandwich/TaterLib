/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata.impl.data.forge;

import cpw.mods.fml.common.Loader;

import dev.neuralnexus.modapi.metadata.Logger;
import dev.neuralnexus.modapi.metadata.Mappings;
import dev.neuralnexus.modapi.metadata.MinecraftVersion;
import dev.neuralnexus.modapi.metadata.ModInfo;
import dev.neuralnexus.modapi.metadata.PlatformData;
import dev.neuralnexus.modapi.metadata.impl.ModInfoImpl;
import dev.neuralnexus.modapi.metadata.impl.logger.ApacheLogger;

import java.util.List;
import java.util.stream.Collectors;

/** Stores data about the CPW Loader platform */
public class CPWLoaderData implements PlatformData {
    @Override
    public MinecraftVersion minecraftVersion() {
        String version = "Unknown";
        try {
            // Reflect to get cpw.mods.fml.common.Loader.MC_VERSION
            version = (String) Loader.class.getField("MC_VERSION").get(null);
        } catch (ReflectiveOperationException ignored) {
        }
        return MinecraftVersion.of(version);
    }

    @Override
    public String modLoaderVersion() {
        return ForgeVersion_7_12.forgeVersion();
    }

    @Override
    public Mappings mappings() {
        return Mappings.SEARGE;
    }

    @Override
    public List<ModInfo> modList() {
        return Loader.instance().getModList().stream()
                .map(
                        modContainer ->
                                new ModInfoImpl(
                                        modContainer.getModId(),
                                        modContainer.getName(),
                                        modContainer.getVersion()))
                .collect(Collectors.toList());
    }

    @Override
    public Logger<org.apache.logging.log4j.Logger> logger(String pluginId) {
        return new ApacheLogger(pluginId);
    }
}
