package dev.neuralnexus.taterlib.neoforge.event.block;

import dev.neuralnexus.taterlib.common.event.Cancellable;
import dev.neuralnexus.taterlib.common.event.block.BlockBreakEvent;

/**
 * NeoForge implementation of {@link BlockBreakEvent}.
 */
public class NeoForgeBlockBreakEvent extends NeoForgeBlockEvent implements BlockBreakEvent, Cancellable {
    private final net.neoforged.neoforge.event.level.BlockEvent.BreakEvent event;

    public NeoForgeBlockBreakEvent(net.neoforged.neoforge.event.level.BlockEvent.BreakEvent.BreakEvent event) {
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
}
