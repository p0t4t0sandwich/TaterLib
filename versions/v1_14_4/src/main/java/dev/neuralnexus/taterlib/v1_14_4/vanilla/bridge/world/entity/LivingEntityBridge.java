/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.world.entity;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

/** LivingEntity bridge interface */
public interface LivingEntityBridge {
    int bridge$getExperienceReward(Player attackingPlayer);

    void bridge$damage(double amount);

    void bridge$damage(double amount, LivingEntity source);

    double bridge$maxHealth();

    void bridge$setMaxHealth(double health);
}
