package dev.neuralnexus.taterlib.v1_20_4.bukkit.listeners.block;

import dev.neuralnexus.taterlib.event.api.BlockEvents;
import dev.neuralnexus.taterlib.v1_20.vanilla.event.block.VanillaPlayerBlockBreakEvent;
import dev.neuralnexus.taterlib.v1_20_4.bukkit.adapters.BukkitAdapter;
import dev.neuralnexus.taterlib.v1_20_4.bukkit.event.BukkitCancellableEventWrapper;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

/** Listens for world events. */
public class BukkitBlockListener implements Listener {
    /**
     * Called when a block is broken.
     *
     * @param event The event.
     */
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        BlockEvents.BLOCK_BREAK.invoke(
                new VanillaPlayerBlockBreakEvent(
                        BukkitAdapter.get().getLevel(event.getBlock().getWorld()),
                        BukkitAdapter.get().getPlayer(event.getPlayer()),
                        BukkitAdapter.get().getBlockPos(event.getBlock()),
                        BukkitAdapter.get().getBlockState(event.getBlock()),
                        new BukkitCancellableEventWrapper<>(event)));
    }
}
