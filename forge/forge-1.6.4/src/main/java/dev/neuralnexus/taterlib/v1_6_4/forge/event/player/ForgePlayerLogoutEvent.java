/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_6_4.forge.event.player;

import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.event.player.PlayerLogoutEvent;
import dev.neuralnexus.taterlib.v1_6_4.forge.entity.player.ForgePlayer;

import net.minecraft.entity.player.EntityPlayer;

/** Forge implementation of {@link PlayerLogoutEvent}. */
public class ForgePlayerLogoutEvent implements PlayerLogoutEvent {
    private final EntityPlayer player;
    private String logoutMessage = "";

    public ForgePlayerLogoutEvent(EntityPlayer player) {
        this.player = player;
    }

    @Override
    public Player player() {
        return new ForgePlayer(player);
    }

    @Override
    public String logoutMessage() {
        if (!this.logoutMessage.isEmpty()) {
            return this.logoutMessage;
        }
        return player.getCommandSenderName() + " left the game";
    }

    @Override
    public void setLogoutMessage(String message) {
        this.logoutMessage = message;
    }
}
