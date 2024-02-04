package dev.neuralnexus.taterlib.v1_20.forge.event;

import dev.neuralnexus.taterlib.event.Cancellable;

import net.minecraftforge.eventbus.api.Event;

/**
 * Wrapper for Forge events that are annotated with {@link
 * net.minecraftforge.eventbus.api.Cancelable}.
 */
public class ForgeCancellableEventWrapper implements Cancellable {
    private final Event event;

    public ForgeCancellableEventWrapper(Event event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isCancelled() {
        return this.event.isCanceled();
    }

    /** {@inheritDoc} */
    @Override
    public void setCancelled(boolean cancelled) {
        this.event.setCanceled(cancelled);
    }
}
