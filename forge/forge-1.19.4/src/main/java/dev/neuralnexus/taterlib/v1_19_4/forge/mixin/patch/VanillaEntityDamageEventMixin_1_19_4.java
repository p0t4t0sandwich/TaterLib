/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_19_4.forge.mixin.patch;

import dev.neuralnexus.taterlib.event.entity.EntityDamageEvent;
import dev.neuralnexus.taterlib.v1_19.vanilla.event.entity.VanillaEntityDamageEvent;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

/** Vanilla implementation of {@link EntityDamageEvent}. */
@Mixin(value = VanillaEntityDamageEvent.class, remap = false)
public class VanillaEntityDamageEventMixin_1_19_4 {
    /**
     * @author Dylan Sperrer (p0t4t0sandwich)
     * @reason Patch for 1.19.4
     */
    @Overwrite
    public String cause() {
        return ((VanillaEntityDamageEvent) (Object) this).source().type().msgId();
    }
}
