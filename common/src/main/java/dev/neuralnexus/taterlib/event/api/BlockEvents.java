package dev.neuralnexus.taterlib.event.api;

import dev.neuralnexus.taterlib.event.block.PlayerBlockBreakEvent;

/** World events. */
public class BlockEvents {
    /** Called when a block is broken. */
    public static final EventHolder<PlayerBlockBreakEvent> BLOCK_BREAK =
            new EventHolder<>(PlayerBlockBreakEvent.class);
}
