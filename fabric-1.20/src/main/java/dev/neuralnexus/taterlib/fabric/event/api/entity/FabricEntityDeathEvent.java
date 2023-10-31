package dev.neuralnexus.taterlib.fabric.event.api.entity;

import dev.neuralnexus.taterlib.common.event.entity.AbstractEntityDeathEvent;
import dev.neuralnexus.taterlib.common.inventory.AbstractItemStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

import java.util.ArrayList;
import java.util.List;

public class FabricEntityDeathEvent extends FabricEntityEvent implements AbstractEntityDeathEvent {
    private final DamageSource source;

    public FabricEntityDeathEvent(Entity entity, DamageSource source) {
        super(entity);
        this.source = source;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<AbstractItemStack> getDrops() {
        return new ArrayList<>();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setDrops(List<AbstractItemStack> drops) {}

    /**
     * @inheritDoc
     */
    @Override
    public void clearDrops() {}

    /**
     * @inheritDoc
     */
    @Override
    public int getDroppedExp() {
        return source.getAttacker() instanceof LivingEntity ? ((LivingEntity) source.getAttacker()).getXpToDrop() : 0;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setDroppedExp(int exp) {}
}
