/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.fabric.event.player;

import dev.neuralnexus.taterapi.event.player.PlayerLoginEvent;

import net.legacyfabric.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.server.MinecraftServer;

/** Fabric implementation of {@link PlayerLoginEvent}. */
public class FabricPlayerLoginEvent extends FabricPlayerEvent implements PlayerLoginEvent {
    private final NetHandlerPlayServer handler;

    public FabricPlayerLoginEvent(
            NetHandlerPlayServer handler, PacketSender sender, MinecraftServer server) {
        super(handler.playerEntity);
        this.handler = handler;
    }

    @Override
    public String loginMessage() {
        return handler.playerEntity.getFormattedCommandSenderName().getFormattedText()
                + " joined the game";
    }

    @Override
    public void setLoginMessage(String message) {}
}
