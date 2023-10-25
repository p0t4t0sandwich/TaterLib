package dev.neuralnexus.taterlib.common.relay;

public interface Message {
    /**
     * Getter for the message
     * @return The message
     */
    String getMessage();

    /**
     * Getter for the timestamp
     * @return The timestamp
     */
    long getTimestamp();
}
