package dev.neuralnexus.taterlib.neoforge.event.entity;

import dev.neuralnexus.taterlib.common.event.entity.EntityDeathEvent;
import dev.neuralnexus.taterlib.common.inventory.ItemStack;
import dev.neuralnexus.taterlib.neoforge.inventory.NeoForgeItemStack;

import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/** NeoForge implementation of {@link EntityDeathEvent}. */
public class NeoForgeEntityDeathEvent extends NeoForgeEntityEvent implements EntityDeathEvent {
    private final LivingDeathEvent event;
    private List<ItemStack> drops = new ArrayList<>();
    private int droppedExp = 0;

    public NeoForgeEntityDeathEvent(LivingDeathEvent event) {
        super(event);
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public List<ItemStack> getDrops() {
        if (!drops.isEmpty()) {
            return drops;
        }
        if (event.getEntity().captureDrops() == null) {
            return new ArrayList<>();
        }
        return event.getEntity().captureDrops().stream()
                .map(itemEntity -> new NeoForgeItemStack(itemEntity.getItem()))
                .collect(Collectors.toList());
    }

    /** {@inheritDoc} */
    @Override
    public void setDrops(List<ItemStack> drops) {
        this.drops = drops;
    }

    /** {@inheritDoc} */
    @Override
    public void clearDrops() {
        drops.clear();
    }

    /** {@inheritDoc} */
    @Override
    public int getDroppedExp() {
        if (droppedExp != 0) {
            return droppedExp;
        }
        return event.getEntity().shouldDropExperience()
                ? event.getEntity().getExperienceReward()
                : 0;
    }

    /** {@inheritDoc} */
    @Override
    public void setDroppedExp(int exp) {
        droppedExp = exp;
    }
}
