/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_14_4.vanilla;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.data.Keys;
import dev.neuralnexus.taterapi.entity.Damageable;
import dev.neuralnexus.taterapi.registries.DataRegistry;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.world.entity.LivingEntityBridge;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.world.VanillaLocation;

/** The Vanilla bootstrap class. */
public class VanillaBootstrap {
    /** Initializes the Vanilla bootstrap. */
    public static void init() {
        TaterAPI.registerBuilder(Location.Builder.class, VanillaLocation.Builder::new);
        DataRegistry.register(Damageable.class, net.minecraft.world.entity.LivingEntity.class)
                .mutable(
                        Keys.ABSORPTION,
                        e -> () -> (double) e.getAbsorptionAmount(),
                        e -> v -> e.setAbsorptionAmount(v.floatValue()))
                .mutable(
                        Keys.HEALTH,
                        e -> () -> (double) e.getHealth(),
                        e -> v -> e.setHealth(v.floatValue()))
                .mutable(
                        Keys.MAX_HEALTH,
                        e -> () -> ((LivingEntityBridge) e).bridge$maxHealth(),
                        e -> v -> ((LivingEntityBridge) e).bridge$maxHealth(v));
    }
}
