package dev.neuralnexus.taterlib.bungee.abstractions.events.player;

import dev.neuralnexus.taterlib.bungee.abstractions.player.BungeePlayer;
import dev.neuralnexus.taterlib.common.abstractions.events.player.AbstractPlayerLoginEvent;
import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;
import net.md_5.bungee.api.event.ServerSwitchEvent;

/**
 * Bungee implementation of {@link AbstractPlayerLoginEvent}.
 */
public class BungeePlayerLoginEvent implements AbstractPlayerLoginEvent {
    private final ServerSwitchEvent event;

    public BungeePlayerLoginEvent(ServerSwitchEvent event) {
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractPlayer getPlayer() {
        AbstractPlayer player = new BungeePlayer(event.getPlayer());
        player.setServerName(event.getPlayer().getServer().getInfo().getName());
        return player;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getLoginMessage() {
        return event.getPlayer() + " joined the game";
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setLoginMessage(String message) {}
}
