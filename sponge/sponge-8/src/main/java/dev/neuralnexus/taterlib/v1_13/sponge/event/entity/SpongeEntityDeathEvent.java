/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_13.sponge.event.entity;

import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.event.entity.EntityDeathEvent;
import dev.neuralnexus.taterapi.item.inventory.ItemStack;
import dev.neuralnexus.taterlib.v1_13.sponge.entity.SpongeEntity;

import org.spongepowered.api.event.entity.DestructEntityEvent;

import java.util.ArrayList;
import java.util.List;

/** Sponge implementation of {@link EntityDeathEvent}. */
public class SpongeEntityDeathEvent implements EntityDeathEvent {
    private final DestructEntityEvent.Death event;

    public SpongeEntityDeathEvent(DestructEntityEvent.Death event) {
        this.event = event;
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

    @Override
    public Entity entity() {
        return new SpongeEntity(event.entity());
    }
}
