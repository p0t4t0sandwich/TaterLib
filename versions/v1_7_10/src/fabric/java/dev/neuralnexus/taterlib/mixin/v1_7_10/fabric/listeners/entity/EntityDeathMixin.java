/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_7_10.fabric.listeners.entity;

import dev.neuralnexus.taterapi.event.api.EntityEvents;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.anno.AConstraint;
import dev.neuralnexus.taterapi.meta.anno.Versions;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.event.entity.VanillaEntityDeathEvent;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.living.LivingEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@AConstraint(
        mappings = Mappings.LEGACY_INTERMEDIARY,
        version = @Versions(min = MinecraftVersion.V7_2, max = MinecraftVersion.V12_2))
@Mixin(LivingEntity.class)
public class EntityDeathMixin {
    @Inject(method = "onKilled", at = @At("HEAD"))
    private void onEntityDeath(DamageSource source, CallbackInfo ci) {
        EntityEvents.DEATH.invoke(
                new VanillaEntityDeathEvent((LivingEntity) (Object) this, source));
    }
}
