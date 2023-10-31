package dev.neuralnexus.taterlib.fabric.event.api.entity;

import dev.neuralnexus.taterlib.common.event.entity.AbstractEntitySpawnEvent;
import dev.neuralnexus.taterlib.common.utils.Location;
import dev.neuralnexus.taterlib.fabric.entity.FabricEntity;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class FabricEntitySpawnEvent extends FabricEntityEvent implements AbstractEntitySpawnEvent {
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
        return new Location(entity.x, entity.y, entity.z, entity.getYaw(0.0F), entity.getPitch(0.0F), getEntity().getDimension());
    }
}
