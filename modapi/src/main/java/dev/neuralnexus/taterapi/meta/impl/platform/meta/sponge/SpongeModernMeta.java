/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.meta.impl.platform.meta.sponge;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.logger.Logger;
import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.logger.impl.ApacheLogger;
import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.meta.MinecraftVersion;
import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.meta.ModInfo;
import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.meta.Platform;
import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.meta.Platforms;
import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.meta.Side;
import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.meta.impl.WMinecraft;
import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.meta.impl.WMinecraftServer;
import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.meta.impl.platform.meta.ModInfoImpl;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.Sponge;
import org.spongepowered.plugin.PluginContainer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/** Stores data about the Sponge platform */
final class SpongeModernMeta implements Platform.Meta {
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
        if (Sponge.server() == null) {
            return Side.CLIENT;
        }
        return WMinecraftServer.isDedicatedServer(Sponge.server()) ? Side.INTEGRATED : Side.SERVER;
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
