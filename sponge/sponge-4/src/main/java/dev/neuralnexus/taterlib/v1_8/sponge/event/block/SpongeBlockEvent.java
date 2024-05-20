package dev.neuralnexus.taterlib.v1_8.sponge.event.block;

import dev.neuralnexus.taterlib.block.Block;
import dev.neuralnexus.taterlib.event.block.BlockEvent;
import dev.neuralnexus.taterlib.v1_8.sponge.block.SpongeBlock;

import org.spongepowered.api.event.block.ChangeBlockEvent;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.List;

/** Sponge implementation of {@link BlockEvent}. */
public class SpongeBlockEvent implements BlockEvent {
    private final ChangeBlockEvent.Pre event;

    public SpongeBlockEvent(ChangeBlockEvent.Pre event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public Block block() {
        List<Location<World>> locations = event.getLocations();
        if (!locations.isEmpty()) {
            Location<World> location = locations.get(0);
            return new SpongeBlock(location);
        }
        return null;
    }
}
