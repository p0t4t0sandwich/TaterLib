package dev.neuralnexus.taterlib.v1_20.bukkit.event.block;

import dev.neuralnexus.taterlib.block.Block;
import dev.neuralnexus.taterlib.event.block.BlockEvent;
import dev.neuralnexus.taterlib.v1_20.bukkit.block.BukkitBlock;

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
