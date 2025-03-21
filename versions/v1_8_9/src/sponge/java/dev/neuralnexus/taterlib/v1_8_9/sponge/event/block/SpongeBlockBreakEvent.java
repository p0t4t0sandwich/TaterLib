/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_8_9.sponge.event.block;

import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.event.block.PlayerBlockBreakEvent;
import dev.neuralnexus.taterlib.v1_8_9.sponge.entity.player.SpongePlayer;

import org.spongepowered.api.event.block.ChangeBlockEvent;

/** Sponge implementation of {@link PlayerBlockBreakEvent}. */
public class SpongeBlockBreakEvent extends SpongeBlockEvent implements PlayerBlockBreakEvent {
    private final ChangeBlockEvent.Pre event;

    public SpongeBlockBreakEvent(ChangeBlockEvent.Pre event) {
        super(event);
        this.event = event;
    }

    @Override
    public boolean cancelled() {
        return this.event.isCancelled();
    }

    @Override
    public void setCancelled(boolean cancelled) {
        event.setCancelled(cancelled);
    }

    @Override
    public Player player() {
        return new SpongePlayer(
                (org.spongepowered.api.entity.living.player.Player) event.getCause().root());
    }
}
