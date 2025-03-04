/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.fabric.event.player;

import dev.neuralnexus.taterapi.event.player.PlayerLogoutEvent;

import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.server.MinecraftServer;

/** Fabric implementation of {@link PlayerLogoutEvent}. */
public class FabricPlayerLogoutEvent extends FabricPlayerEvent implements PlayerLogoutEvent {
    private final NetHandlerPlayServer handler;

    public FabricPlayerLogoutEvent(NetHandlerPlayServer handler, MinecraftServer server) {
        super(handler.playerEntity);
        this.handler = handler;
    }

    @Override
    public String logoutMessage() {
        return handler.playerEntity.getFormattedCommandSenderName().getFormattedText()
                + " left the game";
    }

    @Override
    public void setLogoutMessage(String message) {}
}
