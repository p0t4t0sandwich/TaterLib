/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.vanilla;

import net.minecraft.util.math.Vec3d;

public class VanillaFactories {
    @FunctionalInterface
    public interface Vec3Factory {
        Vec3d create(double x, double y, double z);
    }

    public static Vec3Factory vec3;
}
