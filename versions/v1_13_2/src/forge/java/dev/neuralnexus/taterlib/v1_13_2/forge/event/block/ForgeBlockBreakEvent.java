/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_13_2.forge.event.block;

import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.event.block.PlayerBlockBreakEvent;
import dev.neuralnexus.taterlib.v1_13_2.forge.entity.player.ForgePlayer;

/** Forge implementation of {@link PlayerBlockBreakEvent}. */
public class ForgeBlockBreakEvent extends ForgeBlockEvent implements PlayerBlockBreakEvent {
    private final net.minecraftforge.event.world.BlockEvent.BreakEvent event;

    public ForgeBlockBreakEvent(net.minecraftforge.event.world.BlockEvent.BreakEvent event) {
        super(event);
        this.event = event;
    }

    @Override
    public boolean cancelled() {
        return event.isCanceled();
    }

    @Override
    public void setCancelled(boolean cancelled) {
        event.setCanceled(cancelled);
    }

    @Override
    public Player player() {
        return new ForgePlayer(event.getPlayer());
    }
}
