package dev.neuralnexus.taterlib.bukkit.event.block;

import dev.neuralnexus.taterlib.common.event.block.BlockBreakEvent;

/**
 * Bukkit implementation of {@link BlockBreakEvent}.
 */
public class BukkitBlockBreakEvent extends BukkitBlockEvent implements BlockBreakEvent {
    private final org.bukkit.event.block.BlockBreakEvent event;

    public BukkitBlockBreakEvent(org.bukkit.event.block.BlockBreakEvent event) {
        super(event);
        this.event = event;
    }
}
