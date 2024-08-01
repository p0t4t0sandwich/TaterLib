/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.metadata.impl;

import static dev.neuralnexus.taterapi.util.PathUtils.getPluginsFolder;

import com.velocitypowered.api.network.ProtocolVersion;
import com.velocitypowered.api.proxy.ProxyServer;

import dev.neuralnexus.taterapi.Mappings;
import dev.neuralnexus.taterapi.MinecraftVersion;
import dev.neuralnexus.taterapi.logger.Logger;
import dev.neuralnexus.taterapi.logger.impl.Slf4jLogger;
import dev.neuralnexus.taterapi.metadata.ModInfo;
import dev.neuralnexus.taterapi.metadata.PlatformData;

import org.jetbrains.annotations.ApiStatus;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

/** Stores data about the Velocity platform. */
public class VelocityData implements PlatformData {
    private static ProxyServer proxyServer;

    @ApiStatus.Internal
    public static void setProxyServer(ProxyServer proxyServer) {
        VelocityData.proxyServer = proxyServer;
    }

    @Override
    public MinecraftVersion minecraftVersion() {
        return MinecraftVersion.from(ProtocolVersion.MAXIMUM_VERSION.toString());
    }

    @Override
    public String modLoaderVersion() {
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
                                new ModInfo(
                                        plugin.getDescription().getId(),
                                        plugin.getDescription().getName().orElse("Unknown"),
                                        plugin.getDescription().getVersion().orElse("Unknown")))
                .collect(Collectors.toList());
    }

    @Override
    public Logger logger(String pluginId) {
        return new Slf4jLogger(LoggerFactory.getLogger(pluginId));
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
