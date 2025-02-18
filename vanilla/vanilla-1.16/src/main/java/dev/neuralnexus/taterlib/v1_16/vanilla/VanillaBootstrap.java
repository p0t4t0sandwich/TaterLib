/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_16.vanilla;

import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_16.vanilla.resources.VanillaResourceKey;
import dev.neuralnexus.taterlib.v1_16.vanilla.world.VanillaLocation;

import net.minecraft.Util;

/** The Vanilla bootstrap class. */
public class VanillaBootstrap {
    /** Initializes the Vanilla bootstrap. */
    public static void init() {
        TaterAPIProvider.registerBuilder(Location.Builder.class, VanillaLocation.Builder::new);
        TaterAPIProvider.registerBuilder(
                ResourceKey.Builder.class, VanillaResourceKey.Builder::new);
        TaterAPIProvider.registerFactory(
                ResourceKey.Factory.class, VanillaResourceKey.Factory::new);
        TaterAPIProvider.scheduler()
                .replaceBackgroundScheduler(() -> Util.backgroundExecutor(), false);
    }
}
