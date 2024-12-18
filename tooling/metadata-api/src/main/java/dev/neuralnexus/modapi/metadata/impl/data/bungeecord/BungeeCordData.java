/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata.impl.data.bungeecord;

import static dev.neuralnexus.modapi.metadata.impl.util.PathUtils.getPluginsFolder;

import dev.neuralnexus.modapi.metadata.Logger;
import dev.neuralnexus.modapi.metadata.Mappings;
import dev.neuralnexus.modapi.metadata.MinecraftVersion;
import dev.neuralnexus.modapi.metadata.ModInfo;
import dev.neuralnexus.modapi.metadata.PlatformData;
import dev.neuralnexus.modapi.metadata.impl.ModInfoImpl;
import dev.neuralnexus.modapi.metadata.impl.logger.JavaLogger;

import net.md_5.bungee.api.ProxyServer;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

/** Stores data about the BungeeCord platform */
public class BungeeCordData implements PlatformData {
    @Override
    public MinecraftVersion minecraftVersion() {
        return MinecraftVersion.of(ProxyServer.getInstance().getVersion());
    }

    @Override
    public String modLoaderVersion() {
        return ProxyServer.getInstance().getVersion();
    }

    @Override
    public Mappings mappings() {
        return Mappings.NONE;
    }

    @Override
    public List<ModInfo> modList() {
        return ProxyServer.getInstance().getPluginManager().getPlugins().stream()
                .map(
                        plugin ->
                                new ModInfoImpl(
                                        plugin.getDescription().getName(),
                                        plugin.getDescription().getName(),
                                        plugin.getDescription().getVersion()))
                .collect(Collectors.toList());
    }

    @Override
    public Logger<java.util.logging.Logger> logger(String pluginId) {
        return new JavaLogger(pluginId, ProxyServer.getInstance().getLogger());
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