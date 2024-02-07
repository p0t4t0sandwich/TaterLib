package dev.neuralnexus.taterlib.vanilla.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerLogoutEvent;

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
    public String getLogoutMessage() {
        return player.getName().getString() + " left the game";
    }

    /** {@inheritDoc} */
    @Override
    public void setLogoutMessage(String message) {
        // TODO: Implement
    }
}