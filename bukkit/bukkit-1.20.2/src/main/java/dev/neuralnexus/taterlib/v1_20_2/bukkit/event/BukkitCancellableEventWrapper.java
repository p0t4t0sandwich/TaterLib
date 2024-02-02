package dev.neuralnexus.taterlib.v1_20_2.bukkit.event;

import dev.neuralnexus.taterlib.event.Cancellable;

/** Wrapper for Bukkit events that implement {@link org.bukkit.event.Cancellable}. */
public class BukkitCancellableEventWrapper<T extends org.bukkit.event.Cancellable>
        implements Cancellable {
    private final T event;

    public BukkitCancellableEventWrapper(T event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isCancelled() {
        return this.event.isCancelled();
    }

    /** {@inheritDoc} */
    @Override
    public void setCancelled(boolean cancelled) {
        this.event.setCancelled(cancelled);
    }
}
