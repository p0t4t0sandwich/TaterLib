/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_14_4.vanilla;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.world.VanillaLocation;

/** The Vanilla bootstrap class. */
public class VanillaBootstrap {
    /** Initializes the Vanilla bootstrap. */
    public static void init() {
        TaterAPI.registerBuilder(Location.Builder.class, VanillaLocation.Builder::new);
    }
}
