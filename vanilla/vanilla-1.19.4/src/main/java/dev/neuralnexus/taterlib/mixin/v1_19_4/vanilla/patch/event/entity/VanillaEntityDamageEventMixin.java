/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_19_4.vanilla.patch.event.entity;

import dev.neuralnexus.conditionalmixins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.MinecraftVersion;
import dev.neuralnexus.taterapi.event.entity.EntityDamageEvent;
import dev.neuralnexus.taterlib.v1_19.vanilla.event.entity.VanillaEntityDamageEvent;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

/** Vanilla implementation of {@link EntityDamageEvent}. */
@ReqMCVersion(MinecraftVersion.V1_19_4)
@Mixin(value = VanillaEntityDamageEvent.class, remap = false)
public class VanillaEntityDamageEventMixin {
    /**
     * @author Dylan Sperrer (p0t4t0sandwich)
     * @reason Patch for 1.19.4
     */
    @Overwrite
    public String cause() {
        return ((VanillaEntityDamageEvent) (Object) this).source().type().msgId();
    }
}
