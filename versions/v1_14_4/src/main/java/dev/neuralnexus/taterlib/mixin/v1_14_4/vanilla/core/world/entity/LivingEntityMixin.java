/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_14_4.vanilla.core.world.entity;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.anno.AConstraint;
import dev.neuralnexus.taterapi.meta.anno.Versions;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterlib.mixin.v1_14_4.vanilla.accessors.world.entity.LivingEntityAccessor;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.world.entity.LivingEntityBridge;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.monster.SharedMonsterAttributes;
import net.minecraft.world.entity.player.Player;

import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@AConstraint(
        mappings = Mappings.SEARGE,
        version = @Versions(min = MinecraftVersion.V14, max = MinecraftVersion.V15_2))
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin implements LivingEntityBridge {
    // @spotless:off
    @Shadow public abstract boolean shadow$hurt(DamageSource source, float amount);
    @Shadow public abstract AttributeInstance shadow$getAttribute(Attribute attribute);
    // @spotless:on

    @Override
    public int bridge$getExperienceReward(final @NonNull Player attackingPlayer) {
        return ((LivingEntityAccessor) this).invoker$getExperienceReward(attackingPlayer);
    }

    @Override
    public void bridge$damage(final double amount) {
        this.shadow$hurt(DamageSource.GENERIC, (float) amount);
    }

    @Override
    public void bridge$damage(final double amount, final @NonNull LivingEntity source) {
        this.shadow$hurt(DamageSource.mobAttack(source), (float) amount);
    }

    @Override
    public double bridge$maxHealth() {
        return this.shadow$getAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue();
    }

    @Override
    public void bridge$maxHealth(final double health) {
        this.shadow$getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(health);
    }
}
