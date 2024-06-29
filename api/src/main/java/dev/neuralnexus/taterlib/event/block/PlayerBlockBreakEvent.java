/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.event.block;

import dev.neuralnexus.taterlib.event.Cancellable;
import dev.neuralnexus.taterlib.event.player.PlayerEvent;

/** Abstract representation of a block break event. */
public interface PlayerBlockBreakEvent extends BlockEvent, PlayerEvent, Cancellable {}
