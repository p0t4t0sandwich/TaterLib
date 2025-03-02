/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.utils.neoforge.event;

import dev.neuralnexus.taterapi.event.Cancellable;

import net.neoforged.bus.api.ICancellableEvent;

/** Wrapper for NeoForge events that implement {@link net.neoforged.bus.api.ICancellableEvent}. */
public class NeoForgeCancellableEventWrapper implements Cancellable {
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
}
