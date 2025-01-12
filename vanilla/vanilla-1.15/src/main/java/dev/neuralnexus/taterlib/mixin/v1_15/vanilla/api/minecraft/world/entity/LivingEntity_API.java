/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_15.vanilla.api.minecraft.world.entity;

import dev.neuralnexus.modapi.metadata.Mappings;
import dev.neuralnexus.modapi.metadata.enums.MinecraftVersion;
import dev.neuralnexus.modapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.modapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.entity.Damageable;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.LivingEntity;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.monster.SharedMonsterAttributes;

import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Interface.Remap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@ReqMappings(Mappings.MOJANG)
@ReqMCVersion(min = MinecraftVersion.V15, max = MinecraftVersion.V15_2)
@Mixin(net.minecraft.world.entity.LivingEntity.class)
@Implements({
    @Interface(iface = Damageable.class, prefix = "damageable$", remap = Remap.NONE),
    @Interface(iface = LivingEntity.class, prefix = "livingEntity$", remap = Remap.NONE)
})
@SuppressWarnings({"unused", "UnusedMixin"})
public abstract class LivingEntity_API {
    @Shadow
    public abstract boolean shadow$hurt(DamageSource damageSource, float damage);

    @Shadow
    public abstract float shadow$getHealth();

    @Shadow
    public abstract void shadow$setHealth(float health);

    @Shadow
    public abstract float shadow$getAbsorptionAmount();

    @Shadow
    public abstract void shadow$setAbsorptionAmount(float amount);

    @Shadow
    public abstract AttributeInstance shadow$getAttribute(Attribute attribute);

    public void damageable$damage(double amount) {
        this.shadow$hurt(DamageSource.GENERIC, (float) amount);
    }

    public void damageable$damage(double amount, Entity source) {
        this.shadow$hurt(
                DamageSource.mobAttack((net.minecraft.world.entity.LivingEntity) source),
                (float) amount);
    }

    public double damageable$health() {
        return this.shadow$getHealth();
    }

    public void damageable$setHealth(double health) {
        this.shadow$setHealth((float) health);
    }

    public double damageable$absorptionAmount() {
        return this.shadow$getAbsorptionAmount();
    }

    public void damageable$setAbsorptionAmount(double amount) {
        this.shadow$setAbsorptionAmount((float) amount);
    }

    public double damageable$maxHealth() {
        return this.shadow$getAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue();
    }

    public void damageable$setMaxHealth(double health) {
        this.shadow$getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(health);
    }
}
