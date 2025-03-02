/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_12_2.fabric.event.entity;

import dev.neuralnexus.taterapi.event.entity.EntityDeathEvent;
import dev.neuralnexus.taterapi.item.inventory.ItemStack;

import net.minecraft.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class FabricEntityDeathEvent extends FabricEntityEvent implements EntityDeathEvent {

    public FabricEntityDeathEvent(Entity entity) {
        super(entity);
    }

    @Override
    public List<ItemStack> drops() {
        return new ArrayList<>();
    }

    @Override
    public void setDrops(List<ItemStack> drops) {}

    @Override
    public void clearDrops() {}

    @Override
    public int droppedExp() {
        return 0;
    }

    @Override
    public void setDroppedExp(int exp) {}
}
