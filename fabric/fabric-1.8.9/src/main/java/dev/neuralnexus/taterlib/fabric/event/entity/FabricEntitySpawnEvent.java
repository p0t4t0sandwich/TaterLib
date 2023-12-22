package dev.neuralnexus.taterlib.fabric.event.entity;

import dev.neuralnexus.taterlib.event.entity.EntitySpawnEvent;
import dev.neuralnexus.taterlib.fabric.entity.FabricEntity;
import dev.neuralnexus.taterlib.fabric.util.FabricLocation;
import dev.neuralnexus.taterlib.utils.Location;

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
    public boolean isCancelled() {
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
    public Location getLocation() {
        return new FabricLocation(((FabricEntity) getEntity()).getEntity());
    }
}
