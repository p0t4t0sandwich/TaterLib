/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.vanilla.event.player;

import dev.neuralnexus.taterapi.event.player.PlayerLoginEvent;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;

import net.minecraft.entity.player.EntityPlayer;

/** Vanilla implementation of {@link PlayerLoginEvent}. */
public class VanillaPlayerLoginEvent extends VanillaPlayerEvent implements PlayerLoginEvent {
    public VanillaPlayerLoginEvent(EntityPlayer player) {
        super(player);
    }

    // TODO: Set up login message replacement system
    @Override
    public String loginMessage() {
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public void setLoginMessage(String message) {
        throw new VersionFeatureNotSupportedException();
    }
}
