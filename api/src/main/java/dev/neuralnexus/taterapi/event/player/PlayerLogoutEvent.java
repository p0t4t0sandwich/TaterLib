/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.event.player;

/** Abstract class for player logout events. */
public interface PlayerLogoutEvent extends PlayerEvent {
    /**
     * Gets the logout message.
     *
     * @return The logout message.
     */
    String logoutMessage();

    /**
     * Sets the logout message.
     *
     * @param message The logout message.
     */
    void setLogoutMessage(String message);
}
