package dev.neuralnexus.taterlib.v1_18.vanilla.event;

import dev.neuralnexus.taterlib.event.Cancellable;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Wrapper for Vanilla mixin events that implement {@link
 * org.spongepowered.asm.mixin.injection.callback.Cancellable}.
 */
public class VanillaCancellableCallbackWrapper implements Cancellable {
    private final CallbackInfo ci;

    public VanillaCancellableCallbackWrapper(CallbackInfo ci) {
        this.ci = ci;
    }

    /** {@inheritDoc} */
    @Override
    public boolean cancelled() {
        return ci.isCancelled();
    }

    /** {@inheritDoc} */
    @Override
    public void setCancelled(boolean cancelled) {
        if (cancelled) {
            ci.cancel();
        }
    }
}
