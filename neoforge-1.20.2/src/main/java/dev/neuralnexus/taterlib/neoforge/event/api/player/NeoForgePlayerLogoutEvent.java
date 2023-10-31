package dev.neuralnexus.taterlib.neoforge.event.api.player;

import dev.neuralnexus.taterlib.common.event.player.PlayerLogoutEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

/**
 * NeoForge implementation of {@link PlayerLogoutEvent}.
 */
public class NeoForgePlayerLogoutEvent extends NeoForgePlayerEvent implements PlayerLogoutEvent {
    private final PlayerEvent.PlayerLoggedOutEvent event;
    private String logoutMessage = "";

    public NeoForgePlayerLogoutEvent(PlayerEvent.PlayerLoggedOutEvent event) {
        super(event);
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getLogoutMessage() {
        if (!this.logoutMessage.isEmpty()) {
            return this.logoutMessage;
        }
        return event.getEntity().getName().getString() + " left the game";
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setLogoutMessage(String message) {
        this.logoutMessage = message;
    }
}
