/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_14_4.vanilla.event.player;

import dev.neuralnexus.taterapi.event.player.PlayerLoginEvent;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;

import net.minecraft.server.level.ServerPlayer;

/** Vanilla implementation of {@link PlayerLoginEvent}. */
public class VanillaPlayerLoginEvent extends VanillaPlayerEvent implements PlayerLoginEvent {
    public VanillaPlayerLoginEvent(ServerPlayer player) {
        super(player);
    }

    @Override
    public String loginMessage() {
        return player().name() + " joined the game";
    }

    @Override
    public void setLoginMessage(String message) {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }
}
