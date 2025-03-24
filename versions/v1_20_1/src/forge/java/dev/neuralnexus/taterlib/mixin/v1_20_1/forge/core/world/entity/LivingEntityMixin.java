/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_20_1.forge.core.world.entity;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.world.entity.LivingEntityBridge;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@ReqMappings(Mappings.SEARGE)
@ReqMCVersion(min = MinecraftVersion.V20, max = MinecraftVersion.V20_4)
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin implements LivingEntityBridge {
    @Shadow
    public abstract boolean shadow$hurt(DamageSource damageSource, float damage);

    @Unique public ServerLevel taterapi$level() {
        return (ServerLevel) ((LivingEntity) (Object) this).level();
    }

    @Shadow
    public abstract AttributeInstance shadow$getAttribute(Attribute attribute);

    @Override
    public int bridge$getExperienceReward(Player attackingPlayer) {
        return ((Object) this) instanceof LivingEntity living ? living.getExperienceReward() : 0;
    }

    @Override
    @SuppressWarnings("resource")
    public void bridge$damage(double amount) {
        this.shadow$hurt(this.taterapi$level().damageSources().generic(), (float) amount);
    }

    @Override
    @SuppressWarnings("resource")
    public void bridge$damage(double amount, LivingEntity source) {
        this.shadow$hurt(this.taterapi$level().damageSources().mobAttack(source), (float) amount);
    }

    @Override
    public double bridge$maxHealth() {
        return this.shadow$getAttribute(Attributes.MAX_HEALTH).getBaseValue();
    }

    @Override
    public void bridge$setMaxHealth(double health) {
        this.shadow$getAttribute(Attributes.MAX_HEALTH).setBaseValue(health);
    }
}
