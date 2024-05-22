package dev.neuralnexus.taterlib.v1_7_10.fabric.event.entity;

import dev.neuralnexus.taterlib.event.entity.EntityDeathEvent;
import dev.neuralnexus.taterlib.inventory.ItemStack;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;

import java.util.ArrayList;
import java.util.List;

public class FabricEntityDeathEvent extends FabricEntityEvent implements EntityDeathEvent {
    private final DamageSource source;

    public FabricEntityDeathEvent(Entity entity, DamageSource source) {
        super(entity);
        this.source = source;
    }

    /** {@inheritDoc} */
    @Override
    public List<ItemStack> drops() {
        return new ArrayList<>();
    }

    /** {@inheritDoc} */
    @Override
    public void setDrops(List<ItemStack> drops) {}

    /** {@inheritDoc} */
    @Override
    public void clearDrops() {}

    /** {@inheritDoc} */
    @Override
    public int droppedExp() {
        return 0;
    }

    /** {@inheritDoc} */
    @Override
    public void setDroppedExp(int exp) {}
}
