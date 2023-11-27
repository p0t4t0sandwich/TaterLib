package dev.neuralnexus.taterlib.bukkit.event.block;

import dev.neuralnexus.taterlib.bukkit.player.BukkitPlayer;
import dev.neuralnexus.taterlib.common.event.block.BlockBreakEvent;
import dev.neuralnexus.taterlib.common.player.Player;

/** Bukkit implementation of {@link BlockBreakEvent}. */
public class BukkitBlockBreakEvent extends BukkitBlockEvent implements BlockBreakEvent {
    private final org.bukkit.event.block.BlockBreakEvent event;

    public BukkitBlockBreakEvent(org.bukkit.event.block.BlockBreakEvent event) {
        super(event);
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isCancelled() {
        return event.isCancelled();
    }

    /** {@inheritDoc} */
    @Override
    public void setCancelled(boolean cancelled) {
        event.setCancelled(cancelled);
    }

    /** {@inheritDoc} */
    @Override
    public Player getPlayer() {
        return new BukkitPlayer(event.getPlayer());
    }
}
