/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.meta.impl.platform.meta;

import static dev.neuralnexus.taterapi.util.PathUtils.getPluginsFolder;

import com.google.inject.Inject;
import com.velocitypowered.api.network.ProtocolVersion;
import com.velocitypowered.api.proxy.ProxyServer;

import dev.neuralnexus.taterapi.logger.Logger;
import dev.neuralnexus.taterapi.logger.impl.Slf4jLogger;
import dev.neuralnexus.taterapi.meta.MinecraftVersion;
import dev.neuralnexus.taterapi.meta.ModInfo;
import dev.neuralnexus.taterapi.meta.Platform;
import dev.neuralnexus.taterapi.meta.Platforms;
import dev.neuralnexus.taterapi.meta.Side;

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
    public boolean isClient() {
        return false;
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
    public @NotNull List<ModInfo> mods() {
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
    public @NotNull Path modsFolder() {
        return getPluginsFolder();
    }

    @Override
    public @NotNull Path configFolder() {
        return getPluginsFolder();
    }
}
