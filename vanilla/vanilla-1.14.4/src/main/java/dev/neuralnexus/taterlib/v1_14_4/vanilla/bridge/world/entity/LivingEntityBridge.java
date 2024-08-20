/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.world.entity;

import dev.neuralnexus.taterlib.mixin.v1_14_4.vanilla.accessors.world.entity.LivingEntityAccessor;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

/** LivingEntity bridge interface */
public interface LivingEntityBridge {
    default int bridge$getExperienceReward(LivingEntity entity, Player attackingPlayer) {
        return ((LivingEntityAccessor) entity).invoker$getExperienceReward(attackingPlayer);
    }
}
