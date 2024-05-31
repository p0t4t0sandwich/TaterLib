package dev.neuralnexus.taterlib.v1_18.vanilla.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerLoginEvent;
import dev.neuralnexus.taterlib.exceptions.VersionFeatureNotSupportedException;

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
    public String loginMessage() {
        return player.getName().getString() + " joined the game";
    }

    /** {@inheritDoc} */
    @Override
    public void setLoginMessage(String message) {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }
}
