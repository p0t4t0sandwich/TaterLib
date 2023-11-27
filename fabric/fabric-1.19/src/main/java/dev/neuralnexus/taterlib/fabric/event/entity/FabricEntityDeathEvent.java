package dev.neuralnexus.taterlib.fabric.event.entity;

import dev.neuralnexus.taterlib.common.event.entity.EntityDeathEvent;
import dev.neuralnexus.taterlib.common.inventory.ItemStack;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
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
    public List<ItemStack> getDrops() {
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
    public int getDroppedExp() {
        return source.getAttacker() instanceof LivingEntity
                ? ((LivingEntity) source.getAttacker()).getXpToDrop()
                : 0;
    }

    /** {@inheritDoc} */
    @Override
    public void setDroppedExp(int exp) {}
}
