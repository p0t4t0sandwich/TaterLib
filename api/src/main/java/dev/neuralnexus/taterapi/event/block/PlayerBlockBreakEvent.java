/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.event.block;

import dev.neuralnexus.taterapi.event.Cancellable;
import dev.neuralnexus.taterapi.event.player.PlayerEvent;

/** Abstract representation of a block break event. */
public interface PlayerBlockBreakEvent extends BlockEvent, PlayerEvent, Cancellable {}
