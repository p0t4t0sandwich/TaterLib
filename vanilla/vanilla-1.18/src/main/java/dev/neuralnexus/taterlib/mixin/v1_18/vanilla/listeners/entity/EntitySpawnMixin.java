/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_18.vanilla.listeners.entity;

import dev.neuralnexus.taterapi.event.api.EntityEvents;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.mixin.MixinCancellableCallbackWrapper;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterlib.v1_18.vanilla.event.entity.VanillaEntitySpawnEvent;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** Mixin for the entity spawn listener. */
@ReqMappings(Mappings.MOJANG)
@ReqMCVersion(min = MinecraftVersion.V18, max = MinecraftVersion.V18_2)
@Mixin(ServerLevel.class)
@SuppressWarnings({"unused", "UnusedMixin"})
class EntitySpawnMixin {
    /** Called when an entity is spawned. */
    @Inject(method = "addFreshEntity", at = @At("HEAD"), cancellable = true)
    private void onEntitySpawn(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        EntityEvents.SPAWN.invoke(
                new VanillaEntitySpawnEvent(entity, new MixinCancellableCallbackWrapper(cir)));
    }
}
