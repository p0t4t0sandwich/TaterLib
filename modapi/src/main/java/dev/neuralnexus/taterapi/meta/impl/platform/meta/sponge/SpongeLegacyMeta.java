/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.meta.impl.platform.meta.sponge;

import dev.neuralnexus.taterapi.logger.Logger;
import dev.neuralnexus.taterapi.logger.impl.Slf4jLogger;
import dev.neuralnexus.taterapi.meta.MinecraftVersion;
import dev.neuralnexus.taterapi.meta.ModInfo;
import dev.neuralnexus.taterapi.meta.Platform;
import dev.neuralnexus.taterapi.meta.Platforms;
import dev.neuralnexus.taterapi.meta.Side;
import dev.neuralnexus.taterapi.meta.impl.WMinecraft;
import dev.neuralnexus.taterapi.meta.impl.WMinecraftServer;
import dev.neuralnexus.taterapi.meta.impl.platform.meta.ModInfoImpl;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.plugin.PluginContainer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/** Stores data about the Sponge platform */
final class SpongeLegacyMeta implements Platform.Meta {
    @Override
    public @NotNull Object server() {
        return Sponge.getServer();
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
        if (Sponge.getServer() == null) {
            return Side.CLIENT;
        }
        return WMinecraftServer.isDedicatedServer(Sponge.getServer())
                ? Side.INTEGRATED
                : Side.SERVER;
    }

    @Override
    public @NotNull MinecraftVersion minecraftVersion() {
        return MinecraftVersion.of(Sponge.getPlatform().getMinecraftVersion().getName());
    }

    @Override
    public @NotNull String loaderVersion() {
        Optional<PluginContainer> container = Sponge.getPluginManager().getPlugin("sponge");
        if (container.isPresent()) {
            return container.get().getVersion().toString();
        } else {
            return "Unknown";
        }
    }

    @Override
    public @NotNull String apiVersion() {
        Optional<PluginContainer> container = Sponge.getPluginManager().getPlugin("sponge-api");
        if (container.isPresent()) {
            return container.get().getVersion().toString();
        } else {
            return "Unknown";
        }
    }

    @Override
    public @NotNull List<ModInfo> modList() {
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
    public @NotNull Logger logger(@NotNull String modId) {
        return new Slf4jLogger(modId);
    }
}
