package dev.neuralnexus.taterlib.vanilla.event.entity;

import dev.neuralnexus.taterlib.event.entity.EntitySpawnEvent;
import dev.neuralnexus.taterlib.utils.Location;
import dev.neuralnexus.taterlib.vanilla.util.VanillaLocation;

import net.minecraft.world.entity.Entity;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** Vanilla implementation of {@link EntitySpawnEvent}. */
public class VanillaEntitySpawnEvent extends VanillaEntityEvent implements EntitySpawnEvent {
    private final Entity entity;
    private final CallbackInfoReturnable<Boolean> cir;

    public VanillaEntitySpawnEvent(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        super(entity);
        this.entity = entity;
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
        return new VanillaLocation(entity);
    }
}
