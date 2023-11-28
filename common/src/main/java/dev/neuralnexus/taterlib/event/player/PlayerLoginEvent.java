package dev.neuralnexus.taterlib.event.player;

/** Abstract class for player login events. */
public interface PlayerLoginEvent extends PlayerEvent {
    /**
     * Gets the login message.
     *
     * @return The login message.
     */
    String getLoginMessage();

    /**
     * Sets the login message.
     *
     * @param message The login message.
     */
    void setLoginMessage(String message);
}
