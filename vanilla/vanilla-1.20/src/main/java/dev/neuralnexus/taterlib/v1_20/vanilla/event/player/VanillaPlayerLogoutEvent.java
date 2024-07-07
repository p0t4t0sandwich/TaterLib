/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_20.vanilla.event.player;

import dev.neuralnexus.taterapi.event.player.PlayerLogoutEvent;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;

import net.minecraft.server.level.ServerPlayer;

/** Vanilla implementation of {@link PlayerLogoutEvent}. */
public class VanillaPlayerLogoutEvent extends VanillaPlayerEvent implements PlayerLogoutEvent {
    private final ServerPlayer player;

    public VanillaPlayerLogoutEvent(ServerPlayer player) {
        super(player);
        this.player = player;
    }

    /** {@inheritDoc} */
    @Override
    public String logoutMessage() {
        return player.getName().getString() + " left the game";
    }

    /** {@inheritDoc} */
    @Override
    public void setLogoutMessage(String message) {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }
}
