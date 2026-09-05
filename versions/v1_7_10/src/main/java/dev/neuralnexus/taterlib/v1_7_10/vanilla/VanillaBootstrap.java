/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.vanilla;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.data.Keys;
import dev.neuralnexus.taterapi.entity.Damageable;
import dev.neuralnexus.taterapi.registries.DataRegistry;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.world.VanillaLocation;

import net.minecraft.entity.living.attribute.EntityAttributes;
import net.minecraft.util.math.Vec3d;

/** The Vanilla bootstrap class. */
public class VanillaBootstrap {
    public static void init() {
        TaterAPI.registerBuilder(Location.Builder.class, VanillaLocation.Builder::new);

        VanillaFactories.vec3 = Vec3d::of;

        DataRegistry.register(Damageable.class, net.minecraft.entity.living.LivingEntity.class)
                .mutable(
                        Keys.ABSORPTION,
                        e -> () -> (double) e.getAbsorption(),
                        e -> v -> e.setAbsorption(v.floatValue()))
                .mutable(
                        Keys.HEALTH,
                        e -> () -> (double) e.getHealth(),
                        e -> (v) -> e.setHealth(v.floatValue()))
                .mutable(
                        Keys.MAX_HEALTH,
                        e -> () -> (double) e.getMaxHealth(), // Potential loss in precision
                        e -> (v) -> e.getAttribute(EntityAttributes.MAX_HEALTH).setBase(v));
    }
}
