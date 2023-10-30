package dev.neuralnexus.taterlib.forge.abstrations.events.player;

import dev.neuralnexus.taterlib.common.abstractions.events.player.AbstractPlayerLogoutEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

/**
 * Forge implementation of {@link AbstractPlayerLogoutEvent}.
 */
public class ForgePlayerLogoutEvent extends ForgePlayerEvent implements AbstractPlayerLogoutEvent {
    private final PlayerEvent.PlayerLoggedOutEvent event;
    private String logoutMessage = "";

    public ForgePlayerLogoutEvent(PlayerEvent.PlayerLoggedOutEvent event) {
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
        return event.getPlayer().getName().getString() + " left the game";
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setLogoutMessage(String message) {
        this.logoutMessage = message;
    }
}
