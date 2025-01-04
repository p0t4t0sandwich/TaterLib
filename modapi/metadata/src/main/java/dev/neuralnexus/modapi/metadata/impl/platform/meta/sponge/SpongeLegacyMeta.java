/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata.impl.platform.meta.sponge;

import dev.neuralnexus.modapi.metadata.Logger;
import dev.neuralnexus.modapi.metadata.MinecraftVersion;
import dev.neuralnexus.modapi.metadata.ModInfo;
import dev.neuralnexus.modapi.metadata.Platform;
import dev.neuralnexus.modapi.metadata.Platforms;
import dev.neuralnexus.modapi.metadata.impl.logger.Slf4jLogger;
import dev.neuralnexus.modapi.metadata.impl.platform.meta.ModInfoImpl;

import org.spongepowered.api.Sponge;
import org.spongepowered.plugin.PluginContainer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/** Stores data about the Sponge platform */
public final class SpongeLegacyMeta implements Platform.Meta {
    @Override
    public MinecraftVersion minecraftVersion() {
        return MinecraftVersion.of(Sponge.getPlatform().getMinecraftVersion().getName());
    }

    @Override
    public String loaderVersion() {
        Optional<PluginContainer> container = Sponge.getPluginManager().getPlugin("sponge");
        if (container.isPresent()) {
            return container.get().getVersion().toString();
        } else {
            return "Unknown";
        }
    }

    @Override
    public String apiVersion() {
        Optional<PluginContainer> container = Sponge.getPluginManager().getPlugin("sponge-api");
        if (container.isPresent()) {
            return container.get().getVersion().toString();
        } else {
            return "Unknown";
        }
    }

    @Override
    public List<ModInfo> modList() {
        return Sponge.getPluginManager().getPlugins().stream()
                .map(
                        pluginContainer ->
                                new ModInfoImpl(
                                        pluginContainer.getId(),
                                        pluginContainer.getName(),
                                        pluginContainer.getVersion().orElse("Unknown"),
                                        Platforms.SPONGE))
                .collect(Collectors.toList());
    }

    @Override
    public Logger logger(String modId) {
        return new Slf4jLogger(modId);
    }
}
