/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_6_4.forge.event.entity;

import dev.neuralnexus.taterlib.event.entity.EntityDeathEvent;
import dev.neuralnexus.taterlib.inventory.ItemStack;
import dev.neuralnexus.taterlib.v1_6_4.forge.inventory.ForgeItemStack;

import net.minecraftforge.event.entity.living.LivingDeathEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/** Forge implementation of {@link EntityDeathEvent}. */
public class ForgeEntityDeathEvent extends ForgeEntityEvent implements EntityDeathEvent {
    private final LivingDeathEvent event;
    private List<ItemStack> drops = new ArrayList<>();
    private int droppedExp = 0;

    public ForgeEntityDeathEvent(LivingDeathEvent event) {
        super(event);
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public List<ItemStack> drops() {
        if (!drops.isEmpty()) {
            return drops;
        }
        if (event.entity.capturedDrops == null) {
            return new ArrayList<>();
        }
        return event.entity.capturedDrops.stream()
                .map(itemEntity -> new ForgeItemStack(itemEntity.getEntityItem()))
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
    public int droppedExp() {
        if (droppedExp != 0) {
            return droppedExp;
        }
        return 0;
    }

    /** {@inheritDoc} */
    @Override
    public void setDroppedExp(int exp) {
        droppedExp = exp;
    }
}
