/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.utils.modern.forge.event;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.event.Cancellable;

import net.minecraftforge.eventbus.api.Event;

/**
 * Wrapper for Forge events that are annotated with {@link
 * net.minecraftforge.eventbus.api.Cancelable}.
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
