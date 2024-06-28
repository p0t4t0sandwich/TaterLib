/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_8_9.fabric.event.entity;

import dev.neuralnexus.taterlib.event.entity.EntitySpawnEvent;
import dev.neuralnexus.taterlib.v1_8_9.fabric.entity.FabricEntity;
import dev.neuralnexus.taterlib.v1_8_9.fabric.world.FabricLocation;
import dev.neuralnexus.taterlib.world.Location;

import net.minecraft.entity.Entity;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class FabricEntitySpawnEvent extends FabricEntityEvent implements EntitySpawnEvent {
    private final CallbackInfo ci;

    public FabricEntitySpawnEvent(Entity entity, CallbackInfo ci) {
        super(entity);
        this.ci = ci;
    }

    /** {@inheritDoc} */
    @Override
    public boolean cancelled() {
        return ci.isCancelled();
    }

    /** {@inheritDoc} */
    @Override
    public void setCancelled(boolean cancelled) {
        if (cancelled) {
            ci.cancel();
        }
    }

    /** {@inheritDoc} */
    @Override
    public Location location() {
        return new FabricLocation(((FabricEntity) entity()).entity());
    }
}
