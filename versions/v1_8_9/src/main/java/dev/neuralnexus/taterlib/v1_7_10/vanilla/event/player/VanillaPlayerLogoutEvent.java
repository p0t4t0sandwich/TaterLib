/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.vanilla.event.player;

import dev.neuralnexus.taterapi.event.player.PlayerLogoutEvent;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;

import net.minecraft.entity.player.EntityPlayer;

/** Vanilla implementation of {@link PlayerLogoutEvent}. */
public class VanillaPlayerLogoutEvent extends VanillaPlayerEvent implements PlayerLogoutEvent {
    private final EntityPlayer player;

    public VanillaPlayerLogoutEvent(EntityPlayer player) {
        super(player);
        this.player = player;
    }

    // TODO: Set up logout message replacement system
    @Override
    public String logoutMessage() {
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public void setLogoutMessage(String message) {
        throw new VersionFeatureNotSupportedException();
    }
}
