/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca The project is Licensed under <a
 * href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a> The API is Licensed
 * under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.api.impl.metadata;

import dev.neuralnexus.taterlib.api.MinecraftVersion;
import dev.neuralnexus.taterlib.api.ModInfo;
import dev.neuralnexus.taterlib.api.PlatformData;

import org.spongepowered.api.Sponge;

import java.util.List;
import java.util.stream.Collectors;

/** Stores data about the Sponge platform */
public class SpongeData implements PlatformData {
    @Override
    public MinecraftVersion minecraftVersion() {
        return MinecraftVersion.from(Sponge.platform().minecraftVersion().name());
    }

    @Override
    public String modLoaderVersion() {
        return Sponge.pluginManager().plugin("sponge").get().metadata().version().toString();
    }

    @Override
    public List<ModInfo> modList() {
        return Sponge.pluginManager().plugins().stream()
                .map(
                        pluginContainer ->
                                new ModInfo(
                                        pluginContainer.metadata().id(),
                                        pluginContainer.metadata().name().orElse(""),
                                        pluginContainer.metadata().version().toString()))
                .collect(Collectors.toList());
    }
}
