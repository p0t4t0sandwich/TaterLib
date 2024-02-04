package dev.neuralnexus.taterlib.event.pluginmessages;

/** Wrapper for custom payload packets (plugin messages) */
public interface CustomPayloadWrapper {
    /**
     * Gets the channel.
     *
     * @return The channel.
     */
    String getChannel();

    /**
     * Gets the data.
     *
     * @return The data.
     */
    byte[] getData();
}
