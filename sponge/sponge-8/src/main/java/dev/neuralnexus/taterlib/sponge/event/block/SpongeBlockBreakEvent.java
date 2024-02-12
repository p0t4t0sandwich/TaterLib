package dev.neuralnexus.taterlib.sponge.event.block;

import dev.neuralnexus.taterlib.event.block.PlayerBlockBreakEvent;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.sponge.player.SpongePlayer;

import org.spongepowered.api.event.block.ChangeBlockEvent;

/** Forge implementation of {@link BlockBreakEvent}. */
public class SpongeBlockBreakEvent extends SpongeBlockEvent implements PlayerBlockBreakEvent {
    private final ChangeBlockEvent.Pre event;

    public SpongeBlockBreakEvent(ChangeBlockEvent.Pre event) {
        super(event);
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public boolean cancelled() {
        return event.isCancelled();
    }

    /** {@inheritDoc} */
    @Override
    public void setCancelled(boolean cancelled) {
        event.setCancelled(cancelled);
    }

    /** {@inheritDoc} */
    @Override
    public Player player() {
        return new SpongePlayer(
                (org.spongepowered.api.entity.living.player.Player) event.cause().root());
    }
}
