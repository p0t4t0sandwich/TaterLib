package dev.neuralnexus.taterlib.sponge.event.block;

import dev.neuralnexus.taterlib.common.event.Cancellable;
import dev.neuralnexus.taterlib.common.event.block.BlockBreakEvent;
import org.spongepowered.api.event.block.ChangeBlockEvent;

/**
 * Sponge implementation of {@link BlockBreakEvent}.
 */
public class SpongeBlockBreakEvent extends SpongeBlockEvent implements BlockBreakEvent, Cancellable {
    private final ChangeBlockEvent.Pre event;

    public SpongeBlockBreakEvent(ChangeBlockEvent.Pre event) {
        super(event);
        this.event = event;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCancelled() {
        return event.isCancelled();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCancelled(boolean cancelled) {
        event.setCancelled(cancelled);
    }
}
