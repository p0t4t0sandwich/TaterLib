package dev.neuralnexus.taterlib.forge.event.block;

import dev.neuralnexus.taterlib.event.block.PlayerBlockBreakEvent;
import dev.neuralnexus.taterlib.forge.player.ForgePlayer;
import dev.neuralnexus.taterlib.player.Player;

/** Forge implementation of {@link BlockBreakEvent}. */
public class ForgeBlockBreakEvent extends ForgeBlockEvent implements PlayerBlockBreakEvent {
    private final net.minecraftforge.event.world.BlockEvent.BreakEvent event;

    public ForgeBlockBreakEvent(net.minecraftforge.event.world.BlockEvent.BreakEvent event) {
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
        return new ForgePlayer(event.getPlayer());
    }
}
