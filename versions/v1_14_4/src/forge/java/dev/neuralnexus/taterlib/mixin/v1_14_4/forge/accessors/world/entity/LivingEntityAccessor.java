/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_14_4.forge.accessors.world.entity;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.anno.AConstraint;
import dev.neuralnexus.taterapi.meta.anno.Versions;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@AConstraint(
        mappings = Mappings.SEARGE,
        version = @Versions(min = MinecraftVersion.V14, max = MinecraftVersion.V16_5))
@Mixin(LivingEntity.class)
public interface LivingEntityAccessor {
    @Invoker("getExperienceReward")
    int invoker$getExperienceReward(final Player player);
}
