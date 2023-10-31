package dev.neuralnexus.taterlib.fabric.event.api.player;

import dev.neuralnexus.taterlib.common.event.player.AbstractPlayerLogoutEvent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;

/**
 * Fabric implementation of {@link AbstractPlayerLogoutEvent}.
 */
public class FabricPlayerLogoutEvent extends FabricPlayerEvent implements AbstractPlayerLogoutEvent {
    private final ServerPlayNetworkHandler handler;
    private final MinecraftServer server;

    public FabricPlayerLogoutEvent(ServerPlayNetworkHandler handler, MinecraftServer server) {
        super(handler.player);
        this.handler = handler;
        this.server = server;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getLogoutMessage() {
        return handler.getPlayer().getName().getString() + " left the game";
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setLogoutMessage(String message) {}
}
