/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_8_8.bukkit.event.entity;

import dev.neuralnexus.taterapi.event.entity.EntityDeathEvent;
import dev.neuralnexus.taterapi.inventory.ItemStack;
import dev.neuralnexus.taterlib.v1_8_8.bukkit.inventory.BukkitItemStack;

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
        return event.getDrops().stream().map(BukkitItemStack::new).collect(Collectors.toList());
    }

    /** {@inheritDoc} */
    @Override
    public void setDrops(List<ItemStack> drops) {
        event.getDrops().clear();
        List<org.bukkit.inventory.ItemStack> eventDrops = event.getDrops();
        drops.forEach(item -> eventDrops.add(((BukkitItemStack) item).itemStack()));
    }

    /** {@inheritDoc} */
    @Override
    public void clearDrops() {
        event.getDrops().clear();
    }

    /** {@inheritDoc} */
    @Override
    public int droppedExp() {
        return event.getDroppedExp();
    }

    /** {@inheritDoc} */
    @Override
    public void setDroppedExp(int exp) {
        event.setDroppedExp(exp);
    }
}
