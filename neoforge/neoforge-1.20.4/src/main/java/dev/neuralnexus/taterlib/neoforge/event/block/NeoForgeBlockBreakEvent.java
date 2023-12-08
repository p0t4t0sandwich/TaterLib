package dev.neuralnexus.taterlib.neoforge.event.block;

import dev.neuralnexus.taterlib.event.block.BlockBreakEvent;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.neoforge.player.NeoForgePlayer;

/** NeoForge implementation of {@link BlockBreakEvent}. */
public class NeoForgeBlockBreakEvent extends NeoForgeBlockEvent implements BlockBreakEvent {
    private final net.neoforged.neoforge.event.level.BlockEvent.BreakEvent event;

    public NeoForgeBlockBreakEvent(
            net.neoforged.neoforge.event.level.BlockEvent.BreakEvent.BreakEvent event) {
        super(event);
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isCancelled() {
        return event.isCanceled();
    }

    /** {@inheritDoc} */
    @Override
    public void setCancelled(boolean cancelled) {
        event.setCanceled(cancelled);
    }

    /** {@inheritDoc} */
    @Override
    public Player getPlayer() {
        return new NeoForgePlayer(event.getPlayer());
    }
}
