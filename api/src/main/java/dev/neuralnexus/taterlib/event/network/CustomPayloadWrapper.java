package dev.neuralnexus.taterlib.event.network;

/** Wrapper for custom payload packets (plugin messages) */
public interface CustomPayloadWrapper {
    /**
     * Gets the channel.
     *
     * @return The channel.
     */
    String channel();

    /**
     * Gets the data.
     *
     * @return The data.
     */
    byte[] data();
}
