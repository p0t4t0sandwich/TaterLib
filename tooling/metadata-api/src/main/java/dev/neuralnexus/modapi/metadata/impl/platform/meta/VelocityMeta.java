/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata.impl.platform.meta;

import static dev.neuralnexus.modapi.metadata.impl.util.PathUtils.getPluginsFolder;

import com.velocitypowered.api.network.ProtocolVersion;
import com.velocitypowered.api.proxy.ProxyServer;

import dev.neuralnexus.modapi.metadata.Logger;
import dev.neuralnexus.modapi.metadata.Mappings;
import dev.neuralnexus.modapi.metadata.MinecraftVersion;
import dev.neuralnexus.modapi.metadata.ModInfo;
import dev.neuralnexus.modapi.metadata.Platform;
import dev.neuralnexus.modapi.metadata.Platforms;
import dev.neuralnexus.modapi.metadata.impl.logger.Slf4jLogger;

import org.jetbrains.annotations.ApiStatus;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

/** Stores data about the Velocity platform. */
public final class VelocityMeta implements Platform.Meta {
    private static ProxyServer proxyServer;

    @ApiStatus.Internal
    public static void setProxyServer(ProxyServer proxyServer) {
        VelocityMeta.proxyServer = proxyServer;
    }

    @Override
    public MinecraftVersion minecraftVersion() {
        return MinecraftVersion.of(ProtocolVersion.MAXIMUM_VERSION.toString());
    }

    @Override
    public String loaderVersion() {
        return proxyServer.getVersion().getVersion();
    }

    @Override
    public String apiVersion() {
        return proxyServer.getVersion().getVersion();
    }

    @Override
    public Mappings mappings() {
        return Mappings.NONE;
    }

    @Override
    public List<ModInfo> modList() {
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
    public Logger<org.slf4j.Logger> logger(String modId) {
        return new Slf4jLogger(modId);
    }

    @Override
    public Path modFolder() {
        return getPluginsFolder();
    }

    @Override
    public Path configFolder() {
        return getPluginsFolder();
    }
}
