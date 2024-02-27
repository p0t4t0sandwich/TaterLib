package net.neoforged.neoforge.server;

import net.minecraft.server.MinecraftServer;

/** Fake NeoForge lifecycle hooks. */
public class ServerLifecycleHooks {
    public static MinecraftServer getCurrentServer() {
        return null;
    }
}
