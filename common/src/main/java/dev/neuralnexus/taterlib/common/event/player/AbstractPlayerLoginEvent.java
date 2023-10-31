package dev.neuralnexus.taterlib.common.event.player;

/**
 * Abstract class for player login events.
 */
public interface AbstractPlayerLoginEvent extends AbstractPlayerEvent {
    /**
     * Gets the login message.
     * @return The login message.
     */
    String getLoginMessage();

    /**
     * Sets the login message.
     * @param message The login message.
     */
    void setLoginMessage(String message);
}
