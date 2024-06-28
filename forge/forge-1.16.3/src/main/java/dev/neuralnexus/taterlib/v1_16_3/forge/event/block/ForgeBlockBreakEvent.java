/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_16_3.forge.event.block;

import dev.neuralnexus.taterlib.event.block.PlayerBlockBreakEvent;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.v1_16_3.forge.player.ForgePlayer;

/** Forge implementation of {@link PlayerBlockBreakEvent}. */
public class ForgeBlockBreakEvent extends ForgeBlockEvent implements PlayerBlockBreakEvent {
    private final net.minecraftforge.event.world.BlockEvent.BreakEvent event;

    public ForgeBlockBreakEvent(net.minecraftforge.event.world.BlockEvent.BreakEvent event) {
        super(event);
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public boolean cancelled() {
        return event.isCanceled();
    }

    /** {@inheritDoc} */
    @Override
    public void setCancelled(boolean cancelled) {
        event.setCanceled(cancelled);
    }

    /** {@inheritDoc} */
    @Override
    public Player player() {
        return new ForgePlayer(event.getPlayer());
    }
}
