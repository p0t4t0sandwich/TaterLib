/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata.impl.platform.meta;

import static dev.neuralnexus.modapi.metadata.impl.util.PathUtils.getPluginsFolder;

import dev.neuralnexus.modapi.metadata.Logger;
import dev.neuralnexus.modapi.metadata.MinecraftVersion;
import dev.neuralnexus.modapi.metadata.ModInfo;
import dev.neuralnexus.modapi.metadata.Platform;
import dev.neuralnexus.modapi.metadata.Platforms;
import dev.neuralnexus.modapi.metadata.impl.logger.JavaLogger;

import net.md_5.bungee.api.ProxyServer;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

/** Stores data about the BungeeCord platform */
public final class BungeeCordMeta implements Platform.Meta {
    @Override
    public MinecraftVersion minecraftVersion() {
        return MinecraftVersion.of(ProxyServer.getInstance().getGameVersion());
    }

    @Override
    public String loaderVersion() {
        return ProxyServer.getInstance().getVersion();
    }

    @Override
    public String apiVersion() {
        return ProxyServer.getInstance().getVersion();
    }

    @Override
    public List<ModInfo> modList() {
        return ProxyServer.getInstance().getPluginManager().getPlugins().stream()
                .map(
                        plugin ->
                                new ModInfoImpl(
                                        plugin.getDescription().getName(),
                                        plugin.getDescription().getName(),
                                        plugin.getDescription().getVersion(),
                                        Platforms.BUNGEECORD))
                .collect(Collectors.toList());
    }

    @Override
    public Logger logger(String modId) {
        return new JavaLogger(modId, ProxyServer.getInstance().getLogger());
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
