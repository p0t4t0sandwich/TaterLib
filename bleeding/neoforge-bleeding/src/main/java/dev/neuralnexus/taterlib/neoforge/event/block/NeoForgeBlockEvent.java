package dev.neuralnexus.taterlib.neoforge.event.block;

import dev.neuralnexus.taterlib.block.Block;
import dev.neuralnexus.taterlib.event.block.BlockEvent;
import dev.neuralnexus.taterlib.vanilla.block.VanillaBlock;

/** NeoForge implementation of {@link BlockEvent}. */
public class NeoForgeBlockEvent implements BlockEvent {
    private final net.neoforged.neoforge.event.level.BlockEvent.BreakEvent event;

    public NeoForgeBlockEvent(net.neoforged.neoforge.event.level.BlockEvent.BreakEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public Block getBlock() {
        return new VanillaBlock(this.event.getPos(), this.event.getState().getBlock());
    }
}
