package dev.neuralnexus.taterlib.event.player;

import dev.neuralnexus.taterlib.event.Event;
import dev.neuralnexus.taterlib.player.ProxyPlayer;

/** Abstract class for proxy player events */
public interface ProxyPlayerEvent extends Event {
    /**
     * Getter for the player
     *
     * @return The player
     */
    ProxyPlayer getPlayer();
}
