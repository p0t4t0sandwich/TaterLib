/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_8_9.fabric.event.player;

import dev.neuralnexus.taterapi.event.player.PlayerLogoutEvent;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;

/** Fabric implementation of {@link PlayerLogoutEvent}. */
public class FabricPlayerLogoutEvent extends FabricPlayerEvent implements PlayerLogoutEvent {
    private final ServerPlayNetworkHandler handler;

    public FabricPlayerLogoutEvent(ServerPlayNetworkHandler handler, MinecraftServer server) {
        super(handler.player);
        this.handler = handler;
    }

    @Override
    public String logoutMessage() {
        return handler.player.getName().asFormattedString() + " left the game";
    }

    @Override
    public void setLogoutMessage(String message) {}
}
