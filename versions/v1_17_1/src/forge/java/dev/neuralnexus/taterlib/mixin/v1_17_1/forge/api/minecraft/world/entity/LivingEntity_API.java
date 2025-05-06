/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_17_1.forge.api.minecraft.world.entity;

import dev.neuralnexus.taterapi.entity.Damageable;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.LivingEntity;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.world.entity.LivingEntityBridge;

import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Interface.Remap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@ReqMappings(Mappings.SEARGE)
@ReqMCVersion(min = MinecraftVersion.V17, max = MinecraftVersion.V20_4)
@Mixin(net.minecraft.world.entity.LivingEntity.class)
@Implements({
    @Interface(iface = Damageable.class, prefix = "damageable$", remap = Remap.NONE),
    @Interface(iface = LivingEntity.class, prefix = "livingEntity$", remap = Remap.NONE)
})
public abstract class LivingEntity_API implements LivingEntityBridge {
    // @spotless:off
    @Shadow public abstract float shadow$getHealth();
    @Shadow public abstract void shadow$setHealth(float health);
    @Shadow public abstract float shadow$getAbsorptionAmount();
    @Shadow public abstract void shadow$setAbsorptionAmount(float amount);
    // @spotless:on

    public void damageable$damage(double amount) {
        this.bridge$damage(amount);
    }

    public void damageable$damage(double amount, Entity source) {
        this.bridge$damage(amount, (net.minecraft.world.entity.LivingEntity) source);
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
        return this.bridge$maxHealth();
    }

    public void damageable$setMaxHealth(double health) {
        this.bridge$setMaxHealth(health);
    }
}
