package dev.neuralnexus.taterlib.forge.event.api.player;

import dev.neuralnexus.taterlib.common.event.player.PlayerLoginEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

/**
 * Forge implementation of {@link PlayerLoginEvent}.
 */
public class ForgePlayerLoginEvent extends ForgePlayerEvent implements PlayerLoginEvent {
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
