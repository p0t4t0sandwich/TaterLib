package dev.neuralnexus.taterlib.v1_14_4.vanilla;

import dev.neuralnexus.taterapi.server.SimpleServer;

import net.minecraft.client.Minecraft;

import java.util.function.Supplier;

/**
 * Wrapper for client Minecraft instance
 */
public class VanillaClientServerWrapper {
    public static Supplier<SimpleServer> get() {
        return () -> (SimpleServer) Minecraft.getInstance();
    }

    public static boolean hasIntegratedServer() {
        return Minecraft.getInstance().hasSingleplayerServer();
    }
}
