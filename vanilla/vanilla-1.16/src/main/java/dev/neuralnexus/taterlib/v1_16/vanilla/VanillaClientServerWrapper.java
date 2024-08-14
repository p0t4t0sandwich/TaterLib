/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_16.vanilla;

import dev.neuralnexus.taterapi.server.SimpleServer;

import net.minecraft.client.Minecraft;

import java.util.function.Supplier;

/** Wrapper for client Minecraft instance */
public class VanillaClientServerWrapper {
    public static Supplier<SimpleServer> get() {
        return () -> (SimpleServer) Minecraft.getInstance();
    }

    public static boolean hasIntegratedServer() {
        return Minecraft.getInstance().hasSingleplayerServer();
    }
}
