package dev.neuralnexus.taterlib.event.player;

/** Abstract class for player server switch events. */
public interface PlayerServerSwitchEvent extends ProxyPlayerEvent {
    /**
     * Gets the server the player is switching to.
     *
     * @return The server the player is switching to.
     */
    String toServer();

    /**
     * Gets the server the player is switching from.
     *
     * @return The server the player is switching from.
     */
    String fromServer();
}
