/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_14_4.vanilla.listeners.entity;

import dev.neuralnexus.taterapi.event.api.EntityEvents;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.anno.AConstraint;
import dev.neuralnexus.taterapi.meta.anno.AConstraints;
import dev.neuralnexus.taterapi.meta.anno.Versions;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.meta.enums.Platform;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.event.entity.VanillaEntityDeathEvent;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@AConstraints({
    @AConstraint(
            platform = {Platform.NEOFORGE, Platform.SPONGE},
            invert = true),
    @AConstraint(mappings = Mappings.MOJANG, version = @Versions(min = MinecraftVersion.V14))
})
@Mixin(LivingEntity.class)
public class EntityDeathMixin {
    @Inject(method = "die", at = @At("HEAD"))
    private void onEntityDeath(DamageSource source, CallbackInfo ci) {
        EntityEvents.DEATH.invoke(
                new VanillaEntityDeathEvent((LivingEntity) (Object) this, source));
    }
}
