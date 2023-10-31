package dev.neuralnexus.taterlib.neoforge.abstractions.events.entity;

import dev.neuralnexus.taterlib.common.abstractions.events.entity.AbstractEntityDeathEvent;
import dev.neuralnexus.taterlib.common.abstractions.item.AbstractItemStack;
import dev.neuralnexus.taterlib.neoforge.abstractions.item.NeoForgeItemStack;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * NeoForge implementation of {@link AbstractEntityDeathEvent}.
 */
public class NeoForgeEntityDeathEvent extends NeoForgeEntityEvent implements AbstractEntityDeathEvent {
    private final LivingDeathEvent event;
    private List<AbstractItemStack> drops = new ArrayList<>();
    private int droppedExp = 0;

    public NeoForgeEntityDeathEvent(LivingDeathEvent event) {
        super(event);
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<AbstractItemStack> getDrops() {
        if (!drops.isEmpty()) {
            return drops;
        }
        if (event.getEntity().captureDrops() == null) {
            return new ArrayList<>();
        }
        return event.getEntity().captureDrops().stream().map(itemEntity -> new NeoForgeItemStack(itemEntity.getItem())).collect(Collectors.toList());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setDrops(List<AbstractItemStack> drops) {
        this.drops = drops;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void clearDrops() {
        drops.clear();
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getDroppedExp() {
        if (droppedExp != 0) {
            return droppedExp;
        }
        return event.getEntity().shouldDropExperience() ? event.getEntity().getExperienceReward() : 0;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setDroppedExp(int exp) {
        droppedExp = exp;
    }
}
