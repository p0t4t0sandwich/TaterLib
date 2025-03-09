/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_8_9.sponge.event.block;

import dev.neuralnexus.taterapi.block.Block;
import dev.neuralnexus.taterapi.event.block.BlockEvent;
import dev.neuralnexus.taterlib.v1_8_9.sponge.SpongeFactories;

import org.spongepowered.api.event.block.ChangeBlockEvent;

/** Sponge implementation of {@link BlockEvent}. */
public class SpongeBlockEvent implements BlockEvent {
    private final ChangeBlockEvent.Pre event;

    public SpongeBlockEvent(ChangeBlockEvent.Pre event) {
        this.event = event;
    }

    @Override
    public Block block() {
        return SpongeFactories.blockFromEvent.get(this.event);
    }
}
