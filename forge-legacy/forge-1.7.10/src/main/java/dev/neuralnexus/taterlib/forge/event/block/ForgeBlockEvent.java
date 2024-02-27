package dev.neuralnexus.taterlib.forge.event.block;

import dev.neuralnexus.taterlib.block.Block;
import dev.neuralnexus.taterlib.event.block.BlockEvent;
import dev.neuralnexus.taterlib.forge.block.ForgeBlock;

import net.minecraft.util.ChunkCoordinates;

/** Forge implementation of {@link BlockEvent}. */
public class ForgeBlockEvent implements BlockEvent {
    private final net.minecraftforge.event.world.BlockEvent event;

    public ForgeBlockEvent(net.minecraftforge.event.world.BlockEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public Block block() {
        return new ForgeBlock(new ChunkCoordinates(event.x, event.y, event.z), this.event.block);
    }
}
