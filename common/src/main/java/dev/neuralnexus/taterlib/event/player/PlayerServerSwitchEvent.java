package dev.neuralnexus.taterlib.event.player;

import dev.neuralnexus.taterlib.server.Server;

/** Abstract class for player server switch events. */
public interface PlayerServerSwitchEvent extends ProxyPlayerEvent {
    /**
     * Gets the server the player is switching to.
     *
     * @return The server the player is switching to.
     */
    Server toServer();

    /**
     * Gets the server the player is switching from.
     *
     * @return The server the player is switching from.
     */
    Server fromServer();
}
