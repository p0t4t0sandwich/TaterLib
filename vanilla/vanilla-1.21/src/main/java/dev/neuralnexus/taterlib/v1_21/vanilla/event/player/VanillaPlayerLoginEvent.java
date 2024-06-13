package dev.neuralnexus.taterlib.v1_21.vanilla.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerLoginEvent;
import dev.neuralnexus.taterlib.exceptions.VersionFeatureNotSupportedException;

import net.minecraft.server.level.ServerPlayer;

/** Vanilla implementation of {@link PlayerLoginEvent}. */
public class VanillaPlayerLoginEvent extends VanillaPlayerEvent implements PlayerLoginEvent {
    public VanillaPlayerLoginEvent(ServerPlayer player) {
        super(player);
    }

    /** {@inheritDoc} */
    @Override
    public String loginMessage() {
        return player().name() + " joined the game";
    }

    /** {@inheritDoc} */
    @Override
    public void setLoginMessage(String message) {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }
}
