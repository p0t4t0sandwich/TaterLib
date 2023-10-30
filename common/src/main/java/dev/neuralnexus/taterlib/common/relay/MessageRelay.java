package dev.neuralnexus.taterlib.common.relay;

public interface MessageRelay {
    /**
     * Relays a message from a source to all other sources.
     * @param message The message
     */
    void relayMessage(Message message);
}
