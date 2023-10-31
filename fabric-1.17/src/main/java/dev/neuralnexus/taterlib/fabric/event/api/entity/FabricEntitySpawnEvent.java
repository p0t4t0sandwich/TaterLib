package dev.neuralnexus.taterlib.fabric.event.api.entity;

import dev.neuralnexus.taterlib.common.event.entity.EntitySpawnEvent;
import dev.neuralnexus.taterlib.common.utils.Location;
import dev.neuralnexus.taterlib.fabric.entity.FabricEntity;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class FabricEntitySpawnEvent extends FabricEntityEvent implements EntitySpawnEvent {
    private final CallbackInfoReturnable<Boolean> cir;

    public FabricEntitySpawnEvent(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        super(entity);
        this.cir = cir;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isCancelled() {
        return cir.isCancelled();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setCancelled(boolean cancelled) {
        if (cancelled) {
            cir.cancel();
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public Location getLocation() {
        Entity entity = ((FabricEntity) getEntity()).getEntity();
        return new Location(entity.getX(), entity.getY(), entity.getZ(), entity.getYaw(), entity.getPitch(), getEntity().getDimension());
    }
}
