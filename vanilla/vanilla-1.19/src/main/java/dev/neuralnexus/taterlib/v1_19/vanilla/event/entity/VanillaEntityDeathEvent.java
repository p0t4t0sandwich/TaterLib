package dev.neuralnexus.taterlib.v1_19.vanilla.event.entity;

import dev.neuralnexus.taterlib.event.entity.EntityDeathEvent;
import dev.neuralnexus.taterlib.inventory.ItemStack;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;

/** Vanilla implementation of {@link EntityDeathEvent}. */
public class VanillaEntityDeathEvent extends VanillaEntityEvent implements EntityDeathEvent {
    private final DamageSource source;

    public VanillaEntityDeathEvent(Entity entity, DamageSource source) {
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
        return source.getEntity() instanceof LivingEntity
                ? ((LivingEntity) source.getEntity()).getExperienceReward()
                : 0;
    }

    /** {@inheritDoc} */
    @Override
    public void setDroppedExp(int exp) {}
}
