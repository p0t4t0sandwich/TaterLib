/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata.impl;

/** Wraps the MC server instance */
public class WMinecraftServer {
    public static boolean isDedicatedServer(Object server) {
        return MetaAPIImpl.store.invokeMethod("MinecraftServer", "isDedicatedServer", server);
    }
}
