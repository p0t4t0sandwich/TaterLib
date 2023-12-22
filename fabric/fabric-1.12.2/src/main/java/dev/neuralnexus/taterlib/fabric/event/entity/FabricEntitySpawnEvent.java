package dev.neuralnexus.taterlib.fabric.event.entity;

import dev.neuralnexus.taterlib.event.entity.EntitySpawnEvent;
import dev.neuralnexus.taterlib.fabric.entity.FabricEntity;
import dev.neuralnexus.taterlib.fabric.util.FabricLocation;
import dev.neuralnexus.taterlib.utils.Location;

import net.minecraft.entity.Entity;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class FabricEntitySpawnEvent extends FabricEntityEvent implements EntitySpawnEvent {
    private final CallbackInfoReturnable<Boolean> cir;

    public FabricEntitySpawnEvent(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        super(entity);
        this.cir = cir;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isCancelled() {
        return cir.isCancelled();
    }

    /** {@inheritDoc} */
    @Override
    public void setCancelled(boolean cancelled) {
        if (cancelled) {
            cir.cancel();
        }
    }

    /** {@inheritDoc} */
    @Override
    public Location getLocation() {
        return new FabricLocation(((FabricEntity) getEntity()).getEntity());
    }
}
