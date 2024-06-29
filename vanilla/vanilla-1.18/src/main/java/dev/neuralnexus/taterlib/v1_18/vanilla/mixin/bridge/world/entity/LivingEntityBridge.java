package dev.neuralnexus.taterlib.v1_18.vanilla.mixin.bridge.world.entity;

import dev.neuralnexus.taterlib.v1_18.vanilla.mixin.accessors.world.entity.LivingEntityAccessor;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

/**
 * LivingEntity bridge interface
 */
public interface LivingEntityBridge {
    default int bridge$getExperienceReward(LivingEntity entity, Player attackingPlayer) {
        return ((LivingEntityAccessor) entity).invoker$getExperienceReward(attackingPlayer);
    }
}
