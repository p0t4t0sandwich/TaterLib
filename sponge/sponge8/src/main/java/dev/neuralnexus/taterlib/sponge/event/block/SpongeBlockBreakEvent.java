package dev.neuralnexus.taterlib.sponge.event.block;

import dev.neuralnexus.taterlib.common.event.block.BlockBreakEvent;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.sponge.player.SpongePlayer;

import org.spongepowered.api.event.block.ChangeBlockEvent;

/** Forge implementation of {@link BlockBreakEvent}. */
public class SpongeBlockBreakEvent extends SpongeBlockEvent implements BlockBreakEvent {
    private final ChangeBlockEvent.Pre event;

    public SpongeBlockBreakEvent(ChangeBlockEvent.Pre event) {
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
        return new SpongePlayer(
                (org.spongepowered.api.entity.living.player.Player) event.cause().root());
    }
}
