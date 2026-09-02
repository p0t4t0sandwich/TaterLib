/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.utils.modern.neoforge.event;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.event.Cancellable;

import net.neoforged.bus.api.ICancellableEvent;

/** Wrapper for NeoForge events that implement {@link net.neoforged.bus.api.ICancellableEvent}. */
public class NeoForgeCancellableEventWrapper implements Cancellable, Wrapped<ICancellableEvent> {
    private final ICancellableEvent event;

    public NeoForgeCancellableEventWrapper(ICancellableEvent event) {
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
    public ICancellableEvent unwrap() {
        return this.event;
    }
}
