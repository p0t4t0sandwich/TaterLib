package dev.neuralnexus.taterlib.v1_10_2.forge.event.block;

import dev.neuralnexus.taterlib.block.Block;
import dev.neuralnexus.taterlib.event.block.BlockEvent;
import dev.neuralnexus.taterlib.v1_10_2.forge.block.ForgeBlock;

/** Forge implementation of {@link BlockEvent}. */
public class ForgeBlockEvent implements BlockEvent {
  private final net.minecraftforge.event.world.BlockEvent event;

  public ForgeBlockEvent(net.minecraftforge.event.world.BlockEvent event) {
    this.event = event;
  }

  /** {@inheritDoc} */
  @Override
  public Block block() {
    return new ForgeBlock(this.event.getPos(), this.event.getState().getBlock());
  }
}
