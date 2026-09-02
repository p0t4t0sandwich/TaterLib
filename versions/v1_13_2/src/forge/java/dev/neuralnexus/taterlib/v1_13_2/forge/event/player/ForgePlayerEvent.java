/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_13_2.forge.event.player;

import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.event.player.PlayerEvent;
import dev.neuralnexus.taterlib.v1_13_2.forge.entity.player.ForgePlayer;

/** Forge implementation of {@link PlayerEvent}. */
public class ForgePlayerEvent implements PlayerEvent {
    private final net.minecraftforge.fml.common.gameevent.PlayerEvent event;

    public ForgePlayerEvent(net.minecraftforge.fml.common.gameevent.PlayerEvent event) {
        this.event = event;
    }

    @Override
    public Player player() {
        return new ForgePlayer(event.getPlayer());
    }
}
