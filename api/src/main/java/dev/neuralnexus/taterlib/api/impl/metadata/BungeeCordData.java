/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca The project is Licensed under <a
 * href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a> The API is Licensed
 * under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.api.impl.metadata;

import static dev.neuralnexus.taterlib.utils.PathUtils.getPluginsFolder;

import dev.neuralnexus.taterlib.api.MinecraftVersion;
import dev.neuralnexus.taterlib.api.ModInfo;
import dev.neuralnexus.taterlib.api.PlatformData;

import net.md_5.bungee.api.ProxyServer;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

/** Stores data about the BungeeCord platform */
public class BungeeCordData implements PlatformData {
    @Override
    public MinecraftVersion minecraftVersion() {
        return MinecraftVersion.from(ProxyServer.getInstance().getVersion());
    }

    @Override
    public String modLoaderVersion() {
        return ProxyServer.getInstance().getVersion();
    }

    @Override
    public List<ModInfo> modList() {
        return ProxyServer.getInstance().getPluginManager().getPlugins().stream()
                .map(
                        plugin ->
                                new ModInfo(
                                        plugin.getDescription().getName(),
                                        plugin.getDescription().getName(),
                                        plugin.getDescription().getVersion()))
                .collect(Collectors.toList());
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
