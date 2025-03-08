/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.forge;

import cpw.mods.fml.common.eventhandler.Event;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.event.Cancellable;

/**
 * Wrapper for Forge events that are annotated with {@link
 * cpw.mods.fml.common.eventhandler.Cancelable}.
 */
public class ForgeCancellableEventWrapper implements Cancellable, Wrapped<Event> {
    private final Event event;

    public ForgeCancellableEventWrapper(Event event) {
        this.event = event;
    }

    @Override
    public boolean cancelled() {
        return this.event.isCanceled();
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.event.setCanceled(cancelled);
    }

    @Override
    public Event unwrap() {
        return this.event;
    }
}
