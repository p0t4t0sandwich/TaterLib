/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_7_10.fabric.event.player;

import dev.neuralnexus.taterapi.event.player.PlayerLoginEvent;

import net.legacyfabric.fabric.api.networking.v1.PacketSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;

/** Fabric implementation of {@link PlayerLoginEvent}. */
public class FabricPlayerLoginEvent extends FabricPlayerEvent implements PlayerLoginEvent {
    private final ServerPlayNetworkHandler handler;
    private final PacketSender sender;
    private final MinecraftServer server;

    public FabricPlayerLoginEvent(
            ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
        super(handler.player);
        this.handler = handler;
        this.sender = sender;
        this.server = server;
    }

    @Override
    public String loginMessage() {
        return handler.player.getName().asFormattedString() + " joined the game";
    }

    @Override
    public void setLoginMessage(String message) {}
}
