package dev.neuralnexus.taterlib.forge.abstrations.events.entity;

import dev.neuralnexus.taterlib.common.abstractions.events.entity.AbstractEntityDeathEvent;
import dev.neuralnexus.taterlib.common.abstractions.item.AbstractItemStack;
import dev.neuralnexus.taterlib.forge.abstrations.item.ForgeItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Forge implementation of {@link AbstractEntityDeathEvent}.
 */
public class ForgeEntityDeathEvent extends ForgeEntityEvent implements AbstractEntityDeathEvent {
    private final LivingDeathEvent event;
    private List<AbstractItemStack> drops = new ArrayList<>();
    private int droppedExp = 0;

    public ForgeEntityDeathEvent(LivingDeathEvent event) {
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
        if (event.getEntity().capturedDrops == null) {
            return new ArrayList<>();
        }
        return event.getEntity().capturedDrops.stream().map(itemEntity -> new ForgeItemStack(itemEntity.getItem())).collect(Collectors.toList());
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
        return 0;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setDroppedExp(int exp) {
        droppedExp = exp;
    }
}
