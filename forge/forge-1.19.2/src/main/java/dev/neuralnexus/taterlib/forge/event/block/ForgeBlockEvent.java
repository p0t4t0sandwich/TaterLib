package dev.neuralnexus.taterlib.forge.event.block;

import dev.neuralnexus.taterlib.block.Block;
import dev.neuralnexus.taterlib.event.block.BlockEvent;
import dev.neuralnexus.taterlib.forge.block.ForgeBlock;

/** Forge implementation of {@link BlockEvent}. */
public class ForgeBlockEvent implements BlockEvent {
    private final net.minecraftforge.event.level.BlockEvent event;

    public ForgeBlockEvent(net.minecraftforge.event.level.BlockEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public Block block() {
        return new ForgeBlock(this.event.getPos(), this.event.getState().getBlock());
    }
}
