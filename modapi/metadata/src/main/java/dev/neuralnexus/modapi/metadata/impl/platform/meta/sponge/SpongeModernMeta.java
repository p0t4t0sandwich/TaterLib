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
import dev.neuralnexus.modapi.metadata.Side;
import dev.neuralnexus.modapi.metadata.impl.WMinecraft;
import dev.neuralnexus.modapi.metadata.impl.logger.ApacheLogger;
import dev.neuralnexus.modapi.metadata.impl.platform.meta.ModInfoImpl;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.Sponge;
import org.spongepowered.plugin.PluginContainer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/** Stores data about the Sponge platform */
public final class SpongeModernMeta implements Platform.Meta {
    @Override
    public @NotNull Object server() {
        return Sponge.server();
    }

    @Override
    public @NotNull Object client() {
        return WMinecraft.getInstance();
    }

    @Override
    public @NotNull Object minecraft() {
        if (this.side().isClient() && WMinecraft.hasServer()) {
            return WMinecraft.getServer();
        }
        return this.server();
    }

    @Override
    public @NotNull Side side() {
        // TODO: Look into parsing the MinecraftServer#isDedicatedServer() to determine the side
        throw new UnsupportedOperationException("This needs to be implemented");
    }

    @Override
    public @NotNull MinecraftVersion minecraftVersion() {
        return MinecraftVersion.of(Sponge.platform().minecraftVersion().name());
    }

    @Override
    public @NotNull String loaderVersion() {
        Optional<PluginContainer> container = Sponge.pluginManager().plugin("sponge");
        if (container.isPresent()) {
            return container.get().metadata().version().toString();
        } else {
            return "Unknown";
        }
    }

    @Override
    public @NotNull String apiVersion() {
        Optional<PluginContainer> container = Sponge.pluginManager().plugin("sponge-api");
        if (container.isPresent()) {
            return container.get().metadata().version().toString();
        } else {
            return "Unknown";
        }
    }

    @Override
    public @NotNull List<ModInfo> modList() {
        return Sponge.pluginManager().plugins().stream()
                .map(
                        pluginContainer ->
                                new ModInfoImpl(
                                        pluginContainer.metadata().id(),
                                        pluginContainer.metadata().name().orElse(""),
                                        pluginContainer.metadata().version().toString(),
                                        Platforms.SPONGE))
                .collect(Collectors.toList());
    }

    @Override
    public @NotNull Logger logger(@NotNull String modId) {
        return new ApacheLogger(modId);
    }
}
