/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package net.minecraft.server;

/** Fake Minecraft server class. */
public class MinecraftServer {
    private static MinecraftServer mcServer;

    public static MinecraftServer getServer() {
        return mcServer;
    }
}
