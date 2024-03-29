package dev.neuralnexus.taterlib.v1_19_4.vanilla.mixin.patch.event.entity;

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
    public String getCause() {
        return ((VanillaEntityDamageEvent) (Object) this).getSource().type().msgId();
    }
}
