/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.mixin;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.event.Cancellable;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Wrapper for mixin events that implement {@link
 * org.spongepowered.asm.mixin.injection.callback.Cancellable}.
 */
public class MixinCancellableCallbackWrapper implements Cancellable, Wrapped<CallbackInfo> {
    private final CallbackInfo ci;

    public MixinCancellableCallbackWrapper(CallbackInfo ci) {
        this.ci = ci;
    }

    @Override
    public boolean cancelled() {
        return this.ci.isCancelled();
    }

    @Override
    public void setCancelled(boolean cancelled) {
        if (cancelled) {
            this.ci.cancel();
        }
    }

    @Override
    public CallbackInfo unwrap() {
        return this.ci;
    }
}
