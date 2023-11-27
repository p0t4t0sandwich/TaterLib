package dev.neuralnexus.taterlib.sponge.event.block;

import dev.neuralnexus.taterlib.common.block.Block;
import dev.neuralnexus.taterlib.common.event.block.BlockEvent;
import dev.neuralnexus.taterlib.sponge.block.SpongeBlock;

import org.spongepowered.api.event.Cause;
import org.spongepowered.api.event.block.ChangeBlockEvent;
import org.spongepowered.api.world.LocatableBlock;
import org.spongepowered.api.world.server.ServerLocation;

/** Sponge implementation of {@link BlockEvent}. */
public class SpongeBlockEvent implements BlockEvent {
    private final ChangeBlockEvent.Pre event;

    public SpongeBlockEvent(ChangeBlockEvent.Pre event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public Block getBlock() {
        Cause cause = event.cause();
        LocatableBlock locatableBlock = cause.first(LocatableBlock.class).orElse(null);
        if (locatableBlock == null) {
            return null;
        }
        ServerLocation location = locatableBlock.serverLocation();
        return new SpongeBlock(location);
    }
}
