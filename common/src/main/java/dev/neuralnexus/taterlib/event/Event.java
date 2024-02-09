package dev.neuralnexus.taterlib.event;

/** Generic event interface. */
public interface Event {
    /** Gets the event name. */
    default String getName() {
        return this.getClass().getSimpleName();
    }
}
