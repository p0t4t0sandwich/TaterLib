package dev.neuralnexus.taterlib.common.event.player;

/** Abstract class for player server switch events. */
public interface PlayerServerSwitchEvent extends PlayerEvent {
    /**
     * Gets the server the player is switching to.
     *
     * @return The server the player is switching to.
     */
    String getToServer();

    /**
     * Gets the server the player is switching from.
     *
     * @return The server the player is switching from.
     */
    String getFromServer();
}
