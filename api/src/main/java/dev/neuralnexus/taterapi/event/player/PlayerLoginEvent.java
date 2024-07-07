/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterapi.event.player;

/** Abstract class for player login events. */
public interface PlayerLoginEvent extends PlayerEvent {
    /**
     * Gets the login message.
     *
     * @return The login message.
     */
    String loginMessage();

    /**
     * Sets the login message.
     *
     * @param message The login message.
     */
    void setLoginMessage(String message);
}
