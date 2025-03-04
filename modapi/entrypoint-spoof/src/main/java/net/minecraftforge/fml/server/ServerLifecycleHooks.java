/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package net.minecraftforge.fml.server;

import net.minecraft.server.MinecraftServer;

/** Fake Forge lifecycle hooks. */
public class ServerLifecycleHooks {
    private static MinecraftServer server;

    public static MinecraftServer getCurrentServer() {
        return server;
    }
}
