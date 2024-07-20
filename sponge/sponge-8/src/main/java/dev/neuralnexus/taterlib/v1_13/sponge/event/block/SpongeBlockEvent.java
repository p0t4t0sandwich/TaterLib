/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_13.sponge.event.block;

import dev.neuralnexus.taterapi.block.Block;
import dev.neuralnexus.taterapi.event.block.BlockEvent;
import dev.neuralnexus.taterlib.v1_13.sponge.block.SpongeBlock;

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

    @Override
    public Block block() {
        Cause cause = event.cause();
        LocatableBlock locatableBlock = cause.first(LocatableBlock.class).orElse(null);
        if (locatableBlock == null) {
            return null;
        }
        ServerLocation location = locatableBlock.serverLocation();
        return new SpongeBlock(location);
    }
}
