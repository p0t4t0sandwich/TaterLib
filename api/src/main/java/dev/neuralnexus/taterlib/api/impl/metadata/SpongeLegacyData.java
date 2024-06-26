/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.api.impl.metadata;

import dev.neuralnexus.taterlib.api.MinecraftVersion;
import dev.neuralnexus.taterlib.api.ModInfo;
import dev.neuralnexus.taterlib.api.PlatformData;
import dev.neuralnexus.taterlib.logger.Logger;

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
    public String modLoaderVersion() {
        return Sponge.getPluginManager().getPlugin("sponge").get().getVersion().toString();
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
