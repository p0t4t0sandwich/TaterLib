package dev.neuralnexus.taterlib.event.block;

import dev.neuralnexus.taterlib.event.Cancellable;
import dev.neuralnexus.taterlib.event.player.PlayerEvent;

/** Abstract representation of a block break event. */
public interface BlockBreakEvent extends BlockEvent, PlayerEvent, Cancellable {}
