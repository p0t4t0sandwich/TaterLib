package dev.neuralnexus.taterlib.v1_13_2.bukkit.listeners.block;

import dev.neuralnexus.taterlib.event.api.BlockEvents;
import dev.neuralnexus.taterlib.v1_13_2.bukkit.event.block.BukkitBlockBreakEvent;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/** Listens for world events. */
public class BukkitBlockListener implements Listener {
    /**
     * Called when a block is broken.
     *
     * @param event The event.
     */
    @EventHandler
    public void onBlockBreak(org.bukkit.event.block.BlockBreakEvent event) {
        BlockEvents.PLAYER_BLOCK_BREAK.invoke(new BukkitBlockBreakEvent(event));
    }
}
