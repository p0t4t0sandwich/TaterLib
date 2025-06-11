/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.meta.impl.platform.meta;

import dev.neuralnexus.taterapi.logger.Logger;
import dev.neuralnexus.taterapi.logger.impl.SystemLogger;
import dev.neuralnexus.taterapi.meta.MinecraftVersion;
import dev.neuralnexus.taterapi.meta.ModInfo;
import dev.neuralnexus.taterapi.meta.Platform;
import dev.neuralnexus.taterapi.meta.Side;
import dev.neuralnexus.taterapi.meta.impl.WMinecraft;
import dev.neuralnexus.taterapi.meta.impl.WMinecraftServer;
import dev.neuralnexus.taterapi.util.MixinServiceUtil;

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
        if (this.side().isServer()) {
            return this.minecraft();
        }
        return this.client();
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
        if (server == null) {
            return Side.CLIENT;
        }
        return WMinecraftServer.isDedicatedServer(server) ? Side.INTEGRATED : Side.SERVER;
    }

    @Override
    public boolean isClient() {
        return this.side().isClient();
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
