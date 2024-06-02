package dev.neuralnexus.taterlib.mixin;

import dev.neuralnexus.taterlib.event.Cancellable;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Wrapper for mixin events that implement {@link
 * org.spongepowered.asm.mixin.injection.callback.Cancellable}.
 */
public class MixinCancellableCallbackWrapper implements Cancellable {
    private final CallbackInfo ci;

    public MixinCancellableCallbackWrapper(CallbackInfo ci) {
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
