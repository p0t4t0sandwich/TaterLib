/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_6_4.bukkit.event.entity;

import dev.neuralnexus.taterapi.event.entity.EntityDeathEvent;
import dev.neuralnexus.taterapi.item.inventory.ItemStack;
import dev.neuralnexus.taterlib.v1_6_4.bukkit.item.inventory.BukkitItemStack;

import java.util.List;
import java.util.stream.Collectors;

/** Bukkit implementation of {@link EntityDeathEvent}. */
public class BukkitEntityDeathEvent extends BukkitEntityEvent implements EntityDeathEvent {
    private final org.bukkit.event.entity.EntityDeathEvent event;

    public BukkitEntityDeathEvent(org.bukkit.event.entity.EntityDeathEvent event) {
        super(event);
        this.event = event;
    }

    @Override
    public List<ItemStack> drops() {
        return this.event.getDrops().stream()
                .map(BukkitItemStack::new)
                .collect(Collectors.toList());
    }

    @Override
    public void setDrops(List<ItemStack> drops) {
        this.event.getDrops().clear();
        List<org.bukkit.inventory.ItemStack> eventDrops = this.event.getDrops();
        drops.forEach(item -> eventDrops.add(((BukkitItemStack) item).unwrap()));
    }

    @Override
    public void clearDrops() {
        this.event.getDrops().clear();
    }

    @Override
    public int droppedExp() {
        return this.event.getDroppedExp();
    }

    @Override
    public void setDroppedExp(int exp) {
        this.event.setDroppedExp(exp);
    }
}
