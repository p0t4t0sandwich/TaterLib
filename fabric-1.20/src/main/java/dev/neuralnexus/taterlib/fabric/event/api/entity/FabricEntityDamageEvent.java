package dev.neuralnexus.taterlib.fabric.event.api.entity;

import dev.neuralnexus.taterlib.common.event.entity.AbstractEntityDamageEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Fabric implementation of {@link AbstractEntityDamageEvent}.
 */
public class FabricEntityDamageEvent extends FabricEntityEvent implements AbstractEntityDamageEvent {
    private final DamageSource damageSource;
    private final float damage;
    private final CallbackInfo ci;

    public FabricEntityDamageEvent(Entity entity, DamageSource damageSource, float damage, CallbackInfo ci) {
        super(entity);
        this.damageSource = damageSource;
        this.damage = damage;
        this.ci = ci;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isCancelled() {
        return ci.isCancelled();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setCancelled(boolean cancelled) {
        if (cancelled) {
            ci.cancel();
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getCause() {
        return damageSource.getName();
    }

    /**
     * @inheritDoc
     */
    @Override
    public double getDamage() {
        return damage;
    }
}
