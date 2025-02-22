/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_8_9.fabric.listeners.entity;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterlib.v1_8_9.fabric.event.api.FabricEntityEvents;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTracker;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@ReqMappings(Mappings.LEGACY_INTERMEDIARY)
@ReqMCVersion(min = MinecraftVersion.V8, max = MinecraftVersion.V8_9)
@Mixin(DamageTracker.class)
class EntityDamageMixin {
    /**
     * Called when an entity is spawned.
     *
     * @param damageSource The damage source.
     * @param damage The damage.
     * @param ci The callback info.
     */
    @Inject(method = "onDamage", at = @At("HEAD"), cancellable = true)
    private void onEntityDamage(
            DamageSource damageSource, float originalHealth, float damage, CallbackInfo ci) {
        FabricEntityEvents.DAMAGE
                .invoker()
                .onEntityDamage(damageSource.getSource(), damageSource, damage, ci);
    }
}
