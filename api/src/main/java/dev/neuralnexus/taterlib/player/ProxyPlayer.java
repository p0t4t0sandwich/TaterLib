package dev.neuralnexus.taterlib.player;

/** Abstracts a proxy player. */
public interface ProxyPlayer extends SimplePlayer {
    /**
     * Connect the player to a server.
     *
     * @param serverName The name of the server to connect to.
     */
    void connect(String serverName);
}
