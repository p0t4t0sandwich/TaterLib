package dev.neuralnexus.taterlib.vanilla.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerLogoutEvent;

import net.minecraft.network.Connection;
import net.minecraft.server.level.ServerPlayer;

/** Fabric implementation of {@link PlayerLogoutEvent}. */
public class VanillaPlayerLogoutEvent extends VanillaPlayerEvent implements PlayerLogoutEvent {
    private final Connection connection;
    private final ServerPlayer player;

    public VanillaPlayerLogoutEvent(Connection connection, ServerPlayer player) {
        super(player);
        this.connection = connection;
        this.player = player;
    }

    /** {@inheritDoc} */
    @Override
    public String getLogoutMessage() {
        return player.getName().getString() + " left the game";
    }

    /** {@inheritDoc} */
    @Override
    public void setLogoutMessage(String message) {
        // TODO: Implement
    }
}
