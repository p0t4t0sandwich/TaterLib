package dev.neuralnexus.taterlib.forge.event.player;

import dev.neuralnexus.taterlib.common.event.player.PlayerLogoutEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

/**
 * Forge implementation of {@link PlayerLogoutEvent}.
 */
public class ForgePlayerLogoutEvent extends ForgePlayerEvent implements PlayerLogoutEvent {
    private final PlayerEvent.PlayerLoggedOutEvent event;
    private String logoutMessage = "";

    public ForgePlayerLogoutEvent(PlayerEvent.PlayerLoggedOutEvent event) {
        super(event);
        this.event = event;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLogoutMessage() {
        if (!this.logoutMessage.isEmpty()) {
            return this.logoutMessage;
        }
        return event.getEntity().getName().getString() + " left the game";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLogoutMessage(String message) {
        this.logoutMessage = message;
    }
}
