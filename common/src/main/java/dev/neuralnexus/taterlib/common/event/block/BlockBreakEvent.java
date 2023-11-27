package dev.neuralnexus.taterlib.common.event.block;

import dev.neuralnexus.taterlib.common.event.Cancellable;
import dev.neuralnexus.taterlib.common.event.player.PlayerEvent;

/** Abstract representation of a block break event. */
public interface BlockBreakEvent extends BlockEvent, PlayerEvent, Cancellable {}
