/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_17_1.forge.api.minecraft.world.entity;

import dev.neuralnexus.taterapi.data.DataHolder;
import dev.neuralnexus.taterapi.data.Key;
import dev.neuralnexus.taterapi.data.Keys;
import dev.neuralnexus.taterapi.data.TaterDataHolder;
import dev.neuralnexus.taterapi.data.value.Value;
import dev.neuralnexus.taterapi.entity.Damageable;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.LivingEntity;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.anno.AConstraint;
import dev.neuralnexus.taterapi.meta.anno.Versions;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.world.entity.LivingEntityBridge;

import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Interface.Remap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;
import java.util.Set;

@AConstraint(
        mappings = Mappings.SEARGE,
        version = @Versions(min = MinecraftVersion.V17, max = MinecraftVersion.V20_4))
@Mixin(net.minecraft.world.entity.LivingEntity.class)
@Implements({
    @Interface(iface = DataHolder.class, prefix = "dataHolder$", remap = Remap.NONE),
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

    // ------------------------------------

    @Unique private final TaterDataHolder taterlib$data = new TaterDataHolder();

    public <E> Optional<E> dataHolder$offer(
            final @NonNull Key<? extends Value<E>> key, final E value) {
        return this.taterlib$data.offer(key, value);
    }

    public <E> Optional<E> dataHolder$get(final @NonNull Key<? extends Value<E>> key) {
        return this.taterlib$data.get(key);
    }

    public Set<Key<?>> dataHolder$getKeys() {
        return this.taterlib$data.getKeys();
    }

    // TODO: Investigate usage on later versions
    @Inject(method = "defineSynchedData()V", at = @At("HEAD"))
    void register(final CallbackInfo ci) {
        final Value<Double> absorption =
                Value.mutableOf(
                        Keys.ABSORPTION,
                        () -> (double) this.shadow$getAbsorptionAmount(),
                        (v) -> this.shadow$setAbsorptionAmount(v.floatValue()));
        final Value<Double> health =
                Value.mutableOf(
                        Keys.HEALTH,
                        () -> (double) this.shadow$getHealth(),
                        (v) -> this.shadow$setHealth(v.floatValue()));
        final Value<Double> maxHealth =
                Value.mutableOf(Keys.MAX_HEALTH, this::bridge$maxHealth, this::bridge$maxHealth);

        this.taterlib$data.register(absorption, health, maxHealth);
    }

    // ------------------------------------

    public void damageable$damage(final double amount) {
        this.bridge$damage(amount);
    }

    public void damageable$damage(final double amount, final @NonNull Entity source) {
        this.bridge$damage(amount, (net.minecraft.world.entity.LivingEntity) source);
    }
}
