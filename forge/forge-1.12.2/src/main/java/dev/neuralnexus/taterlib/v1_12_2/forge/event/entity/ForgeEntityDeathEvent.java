/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_12_2.forge.event.entity;

import dev.neuralnexus.taterapi.event.entity.EntityDeathEvent;
import dev.neuralnexus.taterapi.item.inventory.ItemStack;
import dev.neuralnexus.taterlib.v1_12_2.forge.item.inventory.ForgeItemStack;

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

    @Override
    public List<ItemStack> drops() {
        if (!drops.isEmpty()) {
            return drops;
        }
        if (event.getEntity().capturedDrops == null) {
            return new ArrayList<>();
        }
        return event.getEntity().capturedDrops.stream()
                .map(itemEntity -> new ForgeItemStack(itemEntity.getItem()))
                .collect(Collectors.toList());
    }

    @Override
    public void setDrops(List<ItemStack> drops) {
        this.drops = drops;
    }

    @Override
    public void clearDrops() {
        drops.clear();
    }

    @Override
    public int droppedExp() {
        if (droppedExp != 0) {
            return droppedExp;
        }
        return 0;
    }

    @Override
    public void setDroppedExp(int exp) {
        droppedExp = exp;
    }
}
