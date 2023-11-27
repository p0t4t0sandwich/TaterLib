package dev.neuralnexus.taterlib.common.event.player;

/** Abstract class for player logout events. */
public interface PlayerLogoutEvent extends PlayerEvent {
    /**
     * Gets the logout message.
     *
     * @return The logout message.
     */
    String getLogoutMessage();

    /**
     * Sets the logout message.
     *
     * @param message The logout message.
     */
    void setLogoutMessage(String message);
}
