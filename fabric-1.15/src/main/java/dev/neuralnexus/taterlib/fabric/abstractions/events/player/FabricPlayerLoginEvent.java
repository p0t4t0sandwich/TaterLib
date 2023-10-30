package dev.neuralnexus.taterlib.fabric.abstractions.events.player;

import dev.neuralnexus.taterlib.common.abstractions.events.player.AbstractPlayerLoginEvent;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;

/**
 * Fabric implementation of {@link AbstractPlayerLoginEvent}.
 */
public class FabricPlayerLoginEvent extends FabricPlayerEvent implements AbstractPlayerLoginEvent {
    private final ServerPlayNetworkHandler handler;
    private final PacketSender sender;
    private final MinecraftServer server;

    public FabricPlayerLoginEvent(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
        super(handler.player);
        this.handler = handler;
        this.sender = sender;
        this.server = server;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getLoginMessage() {
        return handler.player.getName().getString() + " joined the game";
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setLoginMessage(String message) {}
}
