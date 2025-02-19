/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata.impl.platform.meta;

import static dev.neuralnexus.modapi.metadata.impl.util.PathUtils.getPluginsFolder;

import com.google.inject.Inject;
import com.velocitypowered.api.network.ProtocolVersion;
import com.velocitypowered.api.proxy.ProxyServer;

import dev.neuralnexus.modapi.metadata.Logger;
import dev.neuralnexus.modapi.metadata.MinecraftVersion;
import dev.neuralnexus.modapi.metadata.ModInfo;
import dev.neuralnexus.modapi.metadata.Platform;
import dev.neuralnexus.modapi.metadata.Platforms;
import dev.neuralnexus.modapi.metadata.Side;
import dev.neuralnexus.modapi.metadata.impl.logger.Slf4jLogger;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

/** Stores data about the Velocity platform. */
public final class VelocityMeta implements Platform.Meta {
    @Inject private ProxyServer proxyServer;

    @Override
    public @NotNull Object server() {
        return proxyServer;
    }

    @Override
    public @NotNull Object client() {
        throw new UnsupportedOperationException("Velocity does not run on the client");
    }

    @Override
    public @NotNull Object minecraft() {
        throw new UnsupportedOperationException("Velocity does not have a MinecraftServer");
    }

    @Override
    public @NotNull Side side() {
        return Side.PROXY;
    }

    @Override
    public @NotNull MinecraftVersion minecraftVersion() {
        return MinecraftVersion.of(ProtocolVersion.MAXIMUM_VERSION.toString());
    }

    @Override
    public @NotNull String loaderVersion() {
        return proxyServer.getVersion().getVersion();
    }

    @Override
    public @NotNull String apiVersion() {
        return proxyServer.getVersion().getVersion();
    }

    @Override
    public @NotNull List<ModInfo> modList() {
        return proxyServer.getPluginManager().getPlugins().stream()
                .map(
                        plugin ->
                                new ModInfoImpl(
                                        plugin.getDescription().getId(),
                                        plugin.getDescription().getName().orElse("Unknown"),
                                        plugin.getDescription().getVersion().orElse("Unknown"),
                                        Platforms.VELOCITY))
                .collect(Collectors.toList());
    }

    @Override
    public @NotNull Logger logger(@NotNull String modId) {
        return new Slf4jLogger(modId);
    }

    @Override
    public @NotNull Path modFolder() {
        return getPluginsFolder();
    }

    @Override
    public @NotNull Path configFolder() {
        return getPluginsFolder();
    }
}
