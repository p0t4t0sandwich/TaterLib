/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_19_4.vanilla.event.entity;

import dev.neuralnexus.taterapi.event.Cancellable;
import dev.neuralnexus.taterapi.event.entity.EntityDamageEvent;
import dev.neuralnexus.taterlib.v1_19.vanilla.event.entity.VanillaEntityDamageEvent;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;

/** Vanilla implementation of {@link EntityDamageEvent}. */
public class VanillaEntityDamageEvent_1_19_4 extends VanillaEntityDamageEvent {
    private final DamageSource damageSource;

    public VanillaEntityDamageEvent_1_19_4(
            Entity entity, DamageSource damageSource, float damage, Cancellable cancel) {
        super(entity, damageSource, damage, cancel);
        this.damageSource = damageSource;
    }

    /** {@inheritDoc} */
    @Override
    public String cause() {
        return damageSource.type().msgId();
    }
}
