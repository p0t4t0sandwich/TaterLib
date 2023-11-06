package dev.neuralnexus.taterlib.forge.event.api.entity;

import dev.neuralnexus.taterlib.common.event.entity.EntityDeathEvent;
import dev.neuralnexus.taterlib.common.inventory.ItemStack;
import dev.neuralnexus.taterlib.forge.inventory.ForgeItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Forge implementation of {@link EntityDeathEvent}.
 */
public class ForgeEntityDeathEvent extends ForgeEntityEvent implements EntityDeathEvent {
    private final LivingDeathEvent event;
    private List<ItemStack> drops = new ArrayList<>();
    private int droppedExp = 0;

    public ForgeEntityDeathEvent(LivingDeathEvent event) {
        super(event);
        this.event = event;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ItemStack> getDrops() {
        if (!drops.isEmpty()) {
            return drops;
        }
        if (event.getEntity().captureDrops() == null) {
            return new ArrayList<>();
        }
        return event.getEntity().captureDrops().stream().map(itemEntity -> new ForgeItemStack(itemEntity.getItem())).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDrops(List<ItemStack> drops) {
        this.drops = drops;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearDrops() {
        drops.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDroppedExp() {
        if (droppedExp != 0) {
            return droppedExp;
        }
        return event.getEntity().shouldDropExperience() ? event.getEntity().getExperienceReward() : 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDroppedExp(int exp) {
        droppedExp = exp;
    }
}
