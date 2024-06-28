/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_13_2.bukkit.event.block;

import dev.neuralnexus.taterlib.block.Block;
import dev.neuralnexus.taterlib.event.block.BlockEvent;
import dev.neuralnexus.taterlib.v1_13_2.bukkit.block.BukkitBlock;

/** Bukkit implementation of {@link BlockEvent}. */
public class BukkitBlockEvent implements BlockEvent {
    private final org.bukkit.event.block.BlockEvent event;

    public BukkitBlockEvent(org.bukkit.event.block.BlockEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public Block block() {
        return new BukkitBlock(event.getBlock());
    }
}
