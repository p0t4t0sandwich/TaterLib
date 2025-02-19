/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata.impl;

import dev.neuralnexus.modapi.metadata.Side;

import net.minecraft.server.MinecraftServer;

/** Wraps the MC client instance */
public class WMinecraft {
    public static Object getInstance() {
        return MetaAPIImpl.store.invokeMethod("Minecraft", "getInstance", null);
    }

    public static boolean hasServer() {
        return MetaAPIImpl.store.invokeMethod("Minecraft", "hasServer", getInstance());
    }

    public static MinecraftServer getServer() {
        return MetaAPIImpl.store.invokeMethod("Minecraft", "getServer", getInstance());
    }

    /**
     * Get the "side" the server is running on
     *
     * @param isClient If the current environment is a client
     * @return The side
     */
    public static Side determineSide(boolean isClient) {
        Side side = Side.SERVER;
        if (isClient) {
            if (hasServer()) {
                side = Side.INTEGRATED;
            } else {
                side = Side.CLIENT;
            }
        }
        return side;
    }
}
