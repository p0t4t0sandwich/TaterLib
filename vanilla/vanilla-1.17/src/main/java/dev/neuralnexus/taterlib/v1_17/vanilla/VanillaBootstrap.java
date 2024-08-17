/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_17.vanilla;

import dev.neuralnexus.taterapi.Side;
import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.server.SimpleServer;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_17.vanilla.resources.VanillaResourceKey;
import dev.neuralnexus.taterlib.v1_17.vanilla.world.VanillaLocation;

import net.minecraft.Util;
import net.minecraft.server.MinecraftServer;

import java.util.function.Supplier;

/** The Vanilla bootstrap class. */
public class VanillaBootstrap {
    /** Initializes the Vanilla bootstrap. */
    public static void init() {
        TaterAPIProvider.registerBuilder(Location.Builder.class, VanillaLocation.Builder::new);
        TaterAPIProvider.registerBuilder(
                ResourceKey.Builder.class, VanillaResourceKey.Builder::new);
        TaterAPIProvider.registerFactory(
                ResourceKey.Factory.class, VanillaResourceKey.Factory::new);
        TaterAPIProvider.scheduler().replaceBackgroundScheduler(Util::backgroundExecutor, false);
    }

    /** Get the instance of the server */
    public static Supplier<SimpleServer> server(Supplier<MinecraftServer> serverSupplier) {
        if (TaterAPIProvider.side().is(Side.CLIENT)) {
            return VanillaClientServerWrapper.get();
        }
        return () -> (SimpleServer) serverSupplier.get();
    }

    /** Get the "side" the server is running on */
    public static Side determineSide(boolean isClient) {
        Side side = Side.SERVER;
        if (isClient) {
            if (VanillaClientServerWrapper.hasIntegratedServer()) {
                side = Side.INTEGRATED;
            } else {
                side = Side.CLIENT;
            }
        }
        return side;
    }
}
