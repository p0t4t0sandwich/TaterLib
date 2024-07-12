/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_18.vanilla.event.player;

import dev.neuralnexus.taterapi.event.player.PlayerEvent;
import dev.neuralnexus.taterapi.player.Player;
import dev.neuralnexus.taterlib.v1_18.vanilla.player.VanillaPlayer;

/** Vanilla implementation of {@link PlayerEvent}. */
public class VanillaPlayerEvent implements PlayerEvent {
    private final net.minecraft.world.entity.player.Player player;

    public VanillaPlayerEvent(net.minecraft.world.entity.player.Player player) {
        this.player = player;
    }

    @Override
    public Player player() {
        return new VanillaPlayer(this.player);
    }
}
