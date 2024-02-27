package dev.neuralnexus.taterlib.event;

/** Abstract class for cancellable events. */
public interface Cancellable {
    /**
     * Gets whether the event is cancelled.
     *
     * @return Whether the event is cancelled.
     */
    boolean cancelled();

    /**
     * Sets whether the event is cancelled.
     *
     * @param cancelled Whether the event is cancelled.
     */
    void setCancelled(boolean cancelled);
}
