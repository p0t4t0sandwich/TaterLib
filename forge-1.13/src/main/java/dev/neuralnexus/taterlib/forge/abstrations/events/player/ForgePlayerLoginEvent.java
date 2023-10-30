package dev.neuralnexus.taterlib.forge.abstrations.events.player;

import dev.neuralnexus.taterlib.common.abstractions.events.player.AbstractPlayerLoginEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

/**
 * Forge implementation of {@link AbstractPlayerLoginEvent}.
 */
public class ForgePlayerLoginEvent extends ForgePlayerEvent implements AbstractPlayerLoginEvent {
    private final PlayerEvent.PlayerLoggedInEvent event;
    private String loginMessage = "";

    public ForgePlayerLoginEvent(PlayerEvent.PlayerLoggedInEvent event) {
        super(event);
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getLoginMessage() {
        if (!this.loginMessage.isEmpty()) {
            return this.loginMessage;
        }
        return event.getPlayer().getName().getString() + " joined the game";
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setLoginMessage(String message) {
        this.loginMessage = message;
    }
}
