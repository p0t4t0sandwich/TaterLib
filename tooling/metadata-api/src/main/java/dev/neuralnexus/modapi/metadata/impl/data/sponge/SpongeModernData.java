/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata.impl.data.sponge;

import dev.neuralnexus.modapi.metadata.Mappings;
import dev.neuralnexus.modapi.metadata.MinecraftVersion;
import dev.neuralnexus.modapi.metadata.MinecraftVersions;
import dev.neuralnexus.modapi.metadata.ModInfo;
import dev.neuralnexus.modapi.metadata.Platform;
import dev.neuralnexus.modapi.metadata.PlatformData;
import dev.neuralnexus.modapi.metadata.impl.ModInfoImpl;
import dev.neuralnexus.modapi.metadata.logger.Logger;

import org.spongepowered.api.Sponge;

import java.util.List;
import java.util.stream.Collectors;

/** Stores data about the Sponge platform */
public class SpongeModernData implements PlatformData {
    @Override
    public MinecraftVersion minecraftVersion() {
        return MinecraftVersion.of(Sponge.platform().minecraftVersion().name());
    }

    @Override
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public String modLoaderVersion() {
        return Sponge.pluginManager().plugin("sponge").get().metadata().version().toString();
    }

    @Override
    public Mappings mappings() {
        if (!Platform.get().isNeoForgeBased()
                && Platform.get().isForgeBased()
                && minecraftVersion().isOlderThan(MinecraftVersions.V20_5)) {
            return Mappings.SEARGE;
        }
        return Mappings.MOJMAP;
    }

    @Override
    public List<ModInfo> modList() {
        return Sponge.pluginManager().plugins().stream()
                .map(
                        pluginContainer ->
                                new ModInfoImpl(
                                        pluginContainer.metadata().id(),
                                        pluginContainer.metadata().name().orElse(""),
                                        pluginContainer.metadata().version().toString()))
                .collect(Collectors.toList());
    }

    @Override
    public Logger logger(String pluginId) {
        return null;
    }
}
