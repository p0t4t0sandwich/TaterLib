package dev.neuralnexus.taterlib.common.abstractions.events.player;

/**
 * Abstract class for player logout events.
 */
public interface AbstractPlayerLogoutEvent extends AbstractPlayerEvent {
    /**
     * Gets the logout message.
     * @return The logout message.
     */
    String getLogoutMessage();

    /**
     * Sets the logout message.
     * @param message The logout message.
     */
    void setLogoutMessage(String message);
}
