/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.vanilla;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.world.VanillaLocation;

import net.minecraft.util.math.Vec3d;

/** The Vanilla bootstrap class. */
// TODO: Need networking listeners
public class VanillaBootstrap {
    public static void init() {
        TaterAPI.registerBuilder(Location.Builder.class, VanillaLocation.Builder::new);

        VanillaFactories.vec3 = Vec3d::of;
    }
}
