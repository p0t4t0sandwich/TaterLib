/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.vanilla;

import net.minecraft.util.Vec3;

public class VanillaFactories {
    @FunctionalInterface
    public interface Vec3Factory {
        Vec3 create(double x, double y, double z);
    }

    public static Vec3Factory vec3;
}
