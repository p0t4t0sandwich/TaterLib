package dev.neuralnexus.taterlib.forge.event.block;

import dev.neuralnexus.taterlib.common.event.block.BlockBreakEvent;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.forge.player.ForgePlayer;

/**
 * Forge implementation of {@link BlockBreakEvent}.
 */
public class ForgeBlockBreakEvent extends ForgeBlockEvent implements BlockBreakEvent {
    private final net.minecraftforge.event.level.BlockEvent.BreakEvent event;

    public ForgeBlockBreakEvent(net.minecraftforge.event.level.BlockEvent.BreakEvent event) {
        super(event);
        this.event = event;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCancelled() {
        return event.isCanceled();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCancelled(boolean cancelled) {
        event.setCanceled(cancelled);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getPlayer() {
        return new ForgePlayer(event.getPlayer());
    }
}
