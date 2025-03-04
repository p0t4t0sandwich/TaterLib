/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_9_4.fabric.event.player;

import dev.neuralnexus.taterapi.event.player.PlayerLoginEvent;

import net.legacyfabric.fabric.api.networking.v1.PacketSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;

/** Fabric implementation of {@link PlayerLoginEvent}. */
public class FabricPlayerLoginEvent extends FabricPlayerEvent implements PlayerLoginEvent {
    private final ServerPlayNetworkHandler handler;

    public FabricPlayerLoginEvent(
            ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
        super(handler.player);
        this.handler = handler;
    }

    @Override
    public String loginMessage() {
        return handler.player.getName().asFormattedString() + " joined the game";
    }

    @Override
    public void setLoginMessage(String message) {}
}
