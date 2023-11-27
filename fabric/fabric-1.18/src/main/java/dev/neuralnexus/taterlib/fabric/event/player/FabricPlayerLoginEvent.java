package dev.neuralnexus.taterlib.fabric.event.player;

import dev.neuralnexus.taterlib.common.event.player.PlayerLoginEvent;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
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

    /** {@inheritDoc} */
    @Override
    public String getLoginMessage() {
        return handler.getPlayer().getName().getString() + " joined the game";
    }

    /** {@inheritDoc} */
    @Override
    public void setLoginMessage(String message) {}
}
