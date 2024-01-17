package dev.neuralnexus.taterlib.vanilla.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerLoginEvent;

import net.minecraft.network.Connection;
import net.minecraft.server.level.ServerPlayer;

/** Vanilla implementation of {@link PlayerLoginEvent}. */
public class VanillaPlayerLoginEvent extends VanillaPlayerEvent implements PlayerLoginEvent {
    private final Connection connection;
    private final ServerPlayer player;

    public VanillaPlayerLoginEvent(Connection connection, ServerPlayer player) {
        super(player);
        this.connection = connection;
        this.player = player;
    }

    /** {@inheritDoc} */
    @Override
    public String getLoginMessage() {
        return player.getName().getString() + " joined the game";
    }

    /** {@inheritDoc} */
    @Override
    public void setLoginMessage(String message) {
        // TODO: Implement
    }
}
