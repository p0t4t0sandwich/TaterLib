package dev.neuralnexus.taterlib.fabric.abstractions.events.entity;

import dev.neuralnexus.taterlib.common.abstractions.events.entity.AbstractEntitySpawnEvent;
import dev.neuralnexus.taterlib.common.abstractions.utils.Location;
import dev.neuralnexus.taterlib.fabric.abstractions.entity.FabricEntity;
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
        return new Location(entity.getX(), entity.getY(), entity.getZ(), entity.getYaw(0.0F), entity.getPitch(0.0F), getEntity().getDimension());
    }
}
