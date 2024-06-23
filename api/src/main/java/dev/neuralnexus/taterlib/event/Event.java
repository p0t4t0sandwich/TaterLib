package dev.neuralnexus.taterlib.event;

/** Generic event interface. */
public interface Event {
    /** Gets the event name. */
    default String name() {
        return this.getClass().getSimpleName();
    }
}
