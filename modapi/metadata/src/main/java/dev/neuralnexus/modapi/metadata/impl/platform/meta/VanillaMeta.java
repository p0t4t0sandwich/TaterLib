/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata.impl.platform.meta;

import dev.neuralnexus.modapi.metadata.Logger;
import dev.neuralnexus.modapi.metadata.MinecraftVersion;
import dev.neuralnexus.modapi.metadata.ModInfo;
import dev.neuralnexus.modapi.metadata.Platform;
import dev.neuralnexus.modapi.metadata.Side;
import dev.neuralnexus.modapi.metadata.impl.WMinecraft;
import dev.neuralnexus.modapi.metadata.impl.logger.SystemLogger;
import dev.neuralnexus.modapi.metadata.impl.util.MixinServiceUtil;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/** Stores information about the vanilla platform */
public final class VanillaMeta implements Platform.Meta {
    private static Object server = null;

    /**
     * Set the server object
     *
     * @param server The server object
     */
    @ApiStatus.Internal
    public static void setServer(Object server) {
        VanillaMeta.server = server;
    }

    @Override
    public @NotNull Object server() {
        return this.minecraft();
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
        if (server == null) {
            throw new IllegalStateException("Server has not been set");
        }
        return server;
    }

    @Override
    public @NotNull Side side() {
        // TODO: Look into parsing the MinecraftServer#isDedicatedServer() to determine the side
        throw new UnsupportedOperationException("This needs to be implemented");
    }

    @Override
    public @NotNull MinecraftVersion minecraftVersion() {
        String version = "Unknown";
        try {
            version = MixinServiceUtil.mcVersion();
        } catch (ClassNotFoundException | IOException ignored) {
        }
        return MinecraftVersion.of(version);
    }

    @Override
    public @NotNull String loaderVersion() {
        return minecraftVersion().toString();
    }

    @Override
    public @NotNull String apiVersion() {
        return minecraftVersion().toString();
    }

    @Override
    public @NotNull List<ModInfo> modList() {
        return Collections.emptyList();
    }

    @Override
    public @NotNull Logger logger(@NotNull String modId) {
        // TODO: Do some version parsing and grab the vanilla logger factory
        return new SystemLogger(modId);
    }
}
