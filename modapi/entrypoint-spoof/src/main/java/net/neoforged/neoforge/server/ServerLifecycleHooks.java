/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package net.neoforged.neoforge.server;

import net.minecraft.server.MinecraftServer;

/** Fake NeoForge lifecycle hooks. */
public class ServerLifecycleHooks {
    private static MinecraftServer server;

    public static MinecraftServer getCurrentServer() {
        return server;
    }
}
