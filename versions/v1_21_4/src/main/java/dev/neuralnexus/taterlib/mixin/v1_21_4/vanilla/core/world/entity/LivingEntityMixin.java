/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_21_4.vanilla.core.world.entity;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.anno.AConstraint;
import dev.neuralnexus.taterapi.meta.anno.Versions;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.world.entity.LivingEntityBridge;

import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@AConstraint(mappings = Mappings.MOJANG, version = @Versions(min = MinecraftVersion.V21_2))
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin implements LivingEntityBridge {
    // @spotless:off
    @Shadow public abstract boolean shadow$hurtServer(ServerLevel serverLevel, DamageSource damageSource, float f);
    @Shadow public abstract AttributeInstance shadow$getAttribute(Holder<Attribute> attribute);
    @Unique public ServerLevel taterapi$level() {
        return (ServerLevel) ((LivingEntity) (Object) this).level();
    }
    // @spotless:on

    @Override
    public int bridge$getExperienceReward(final @NonNull Player attackingPlayer) {
        return ((Object) this) instanceof LivingEntity living
                ? living.getExperienceReward(this.taterapi$level(), attackingPlayer)
                : 0;
    }

    @Override
    @SuppressWarnings("resource")
    public void bridge$damage(final double amount) {
        this.shadow$hurtServer(
                this.taterapi$level(),
                this.taterapi$level().damageSources().generic(),
                (float) amount);
    }

    @Override
    @SuppressWarnings("resource")
    public void bridge$damage(final double amount, final @NonNull LivingEntity source) {
        this.shadow$hurtServer(
                this.taterapi$level(),
                this.taterapi$level().damageSources().mobAttack(source),
                (float) amount);
    }

    @Override
    public double bridge$maxHealth() {
        return this.shadow$getAttribute(Attributes.MAX_HEALTH).getBaseValue();
    }

    @Override
    public void bridge$maxHealth(final double health) {
        this.shadow$getAttribute(Attributes.MAX_HEALTH).setBaseValue(health);
    }
}
