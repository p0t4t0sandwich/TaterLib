package dev.neuralnexus.taterlib.neoforge.event.player;

import dev.neuralnexus.taterlib.common.event.player.PlayerLoginEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

/**
 * NeoForge implementation of {@link PlayerLoginEvent}.
 */
public class NeoForgePlayerLoginEvent extends NeoForgePlayerEvent implements PlayerLoginEvent {
    private final PlayerEvent.PlayerLoggedInEvent event;
    private String loginMessage = "";

    public NeoForgePlayerLoginEvent(PlayerEvent.PlayerLoggedInEvent event) {
        super(event);
        this.event = event;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLoginMessage() {
        if (!this.loginMessage.isEmpty()) {
            return this.loginMessage;
        }
        return event.getEntity().getName().getString() + " joined the game";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLoginMessage(String message) {
        this.loginMessage = message;
    }
}
