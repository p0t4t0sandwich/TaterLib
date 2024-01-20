package dev.neuralnexus.taterlib.event.api;

import dev.neuralnexus.taterlib.event.block.BlockBreakEvent;

/** World events. */
public class BlockEvents {
    /** Called when a block is broken. */
    public static final Event<BlockBreakEvent> BLOCK_BREAK = new Event<>(BlockBreakEvent.class);
}
