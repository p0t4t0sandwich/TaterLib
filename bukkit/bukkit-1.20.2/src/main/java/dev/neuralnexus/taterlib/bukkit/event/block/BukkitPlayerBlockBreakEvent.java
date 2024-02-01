package dev.neuralnexus.taterlib.bukkit.event.block;

import dev.neuralnexus.taterlib.bukkit.player.BukkitPlayer;
import dev.neuralnexus.taterlib.event.block.PlayerBlockBreakEvent;
import dev.neuralnexus.taterlib.player.Player;

/** Bukkit implementation of {@link PlayerBlockBreakEvent}. */
public class BukkitPlayerBlockBreakEvent extends BukkitBlockEvent implements PlayerBlockBreakEvent {
    private final org.bukkit.event.block.BlockBreakEvent event;

    public BukkitPlayerBlockBreakEvent(org.bukkit.event.block.BlockBreakEvent event) {
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
