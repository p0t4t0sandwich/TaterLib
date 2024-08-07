/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.mixin;

import dev.neuralnexus.taterapi.event.Cancellable;

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

    @Override
    public boolean cancelled() {
        return ci.isCancelled();
    }

    @Override
    public void setCancelled(boolean cancelled) {
        if (cancelled) {
            ci.cancel();
        }
    }
}
