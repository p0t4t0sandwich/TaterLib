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
import dev.neuralnexus.modapi.metadata.Side;
import dev.neuralnexus.modapi.metadata.impl.logger.JavaLogger;

import net.md_5.bungee.api.ProxyServer;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

/** Stores data about the BungeeCord platform */
public final class BungeeCordMeta implements Platform.Meta {
    @Override
    public @NotNull Object server() {
        return ProxyServer.getInstance();
    }

    @Override
    public @NotNull Object client() {
        throw new UnsupportedOperationException("BungeeCord does not run on the client");
    }

    @Override
    public @NotNull Object minecraft() {
        throw new UnsupportedOperationException("BungeeCord does not have a MinecraftServer");
    }

    @Override
    public @NotNull Side side() {
        return Side.PROXY;
    }

    @Override
    public @NotNull MinecraftVersion minecraftVersion() {
        return MinecraftVersion.of(ProxyServer.getInstance().getGameVersion());
    }

    @Override
    public @NotNull String loaderVersion() {
        return ProxyServer.getInstance().getVersion();
    }

    @Override
    public @NotNull String apiVersion() {
        return ProxyServer.getInstance().getVersion();
    }

    @Override
    public @NotNull List<ModInfo> modList() {
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
    public @NotNull Logger logger(@NotNull String modId) {
        return new JavaLogger(modId, ProxyServer.getInstance().getLogger());
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
