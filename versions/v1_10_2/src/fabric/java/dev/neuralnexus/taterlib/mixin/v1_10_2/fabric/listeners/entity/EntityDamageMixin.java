/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_10_2.fabric.listeners.entity;

import dev.neuralnexus.taterapi.event.api.EntityEvents;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.mixin.MixinCancellableCallbackWrapper;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterlib.v1_10_2.vanilla.event.entity.VanillaEntityDamageEvent;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTracker;
import net.minecraft.entity.living.LivingEntity;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@ReqMappings(Mappings.LEGACY_INTERMEDIARY)
@ReqMCVersion(min = MinecraftVersion.V9, max = MinecraftVersion.V9_4)
@Mixin(DamageTracker.class)
abstract class EntityDamageMixin {
    @Shadow @Final private LivingEntity entity;

    @Inject(method = "onDamage", at = @At("HEAD"), cancellable = true)
    private void onEntityDamage(
            DamageSource source, float originalHealth, float damage, CallbackInfo ci) {
        EntityEvents.DAMAGE.invoke(
                new VanillaEntityDamageEvent(
                        this.entity,
                        source,
                        originalHealth,
                        damage,
                        new MixinCancellableCallbackWrapper(ci)));
    }
}
