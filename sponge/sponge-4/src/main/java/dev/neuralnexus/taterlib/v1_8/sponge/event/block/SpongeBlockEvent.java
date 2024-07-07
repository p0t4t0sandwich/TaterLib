/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_8.sponge.event.block;

import dev.neuralnexus.taterapi.block.Block;
import dev.neuralnexus.taterapi.event.block.BlockEvent;
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
