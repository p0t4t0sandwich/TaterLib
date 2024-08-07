/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.metadata.impl;

import dev.neuralnexus.taterapi.Mappings;
import dev.neuralnexus.taterapi.MinecraftVersion;
import dev.neuralnexus.taterapi.logger.Logger;
import dev.neuralnexus.taterapi.metadata.ModInfo;
import dev.neuralnexus.taterapi.metadata.PlatformData;

import org.spongepowered.api.Sponge;

import java.util.List;
import java.util.stream.Collectors;

/** Stores data about the Sponge platform */
public class SpongeLegacyData implements PlatformData {
    @Override
    public MinecraftVersion minecraftVersion() {
        return MinecraftVersion.from(Sponge.getPlatform().getMinecraftVersion().getName());
    }

    @Override
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public String modLoaderVersion() {
        return Sponge.getPluginManager().getPlugin("sponge").get().getVersion().toString();
    }

    @Override
    public Mappings mappings() {
        return Mappings.SEARGE;
    }

    @Override
    public List<ModInfo> modList() {
        return Sponge.getPluginManager().getPlugins().stream()
                .map(
                        pluginContainer ->
                                new ModInfo(
                                        pluginContainer.getId(),
                                        pluginContainer.getName(),
                                        pluginContainer.getVersion().orElse("Unknown")))
                .collect(Collectors.toList());
    }

    @Override
    public Logger logger(String pluginId) {
        return null;
    }
}
