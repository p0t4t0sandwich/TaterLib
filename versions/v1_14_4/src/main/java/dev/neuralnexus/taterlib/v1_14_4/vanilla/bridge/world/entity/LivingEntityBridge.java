/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.world.entity;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import org.jspecify.annotations.NonNull;

/** LivingEntity bridge interface */
public interface LivingEntityBridge {
    int bridge$getExperienceReward(final @NonNull Player attackingPlayer);

    void bridge$damage(final double amount);

    void bridge$damage(final double amount, final @NonNull LivingEntity source);

    double bridge$maxHealth();

    void bridge$maxHealth(final double health);
}
