package dev.neuralnexus.taterlib.v1_19.vanilla.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerLogoutEvent;
import dev.neuralnexus.taterlib.exceptions.VersionFeatureNotSupportedException;

import net.minecraft.server.level.ServerPlayer;

/** Vanilla implementation of {@link PlayerLogoutEvent}. */
public class VanillaPlayerLogoutEvent extends VanillaPlayerEvent implements PlayerLogoutEvent {
    private final ServerPlayer player;

    public VanillaPlayerLogoutEvent(ServerPlayer player) {
        super(player);
        this.player = player;
    }

    /** {@inheritDoc} */
    @Override
    public String logoutMessage() {
        return player.getName().getString() + " left the game";
    }

    /** {@inheritDoc} */
    @Override
    public void setLogoutMessage(String message) {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }
}
