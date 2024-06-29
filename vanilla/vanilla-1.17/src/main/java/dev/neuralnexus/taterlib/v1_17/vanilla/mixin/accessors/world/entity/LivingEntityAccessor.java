package dev.neuralnexus.taterlib.v1_17.vanilla.mixin.accessors.world.entity;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

/**
 * Accessor for {@link LivingEntity}.
 */
@Mixin(LivingEntity.class)
public interface LivingEntityAccessor {
    @Invoker("getExperienceReward") int invoker$getExperienceReward(final Player player);
}
