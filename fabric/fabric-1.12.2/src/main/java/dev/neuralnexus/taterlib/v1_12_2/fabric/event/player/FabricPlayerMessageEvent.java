/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_12_2.fabric.event.player;

import dev.neuralnexus.taterapi.entity.player.User;
import dev.neuralnexus.taterapi.event.player.PlayerMessageEvent;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;

import net.minecraft.entity.player.PlayerEntity;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Set;

/** Fabric implementation of {@link PlayerMessageEvent}. */
public class FabricPlayerMessageEvent extends FabricPlayerEvent implements PlayerMessageEvent {
    private final CallbackInfo ci;
    private String message;

    public FabricPlayerMessageEvent(PlayerEntity player, String message, CallbackInfo ci) {
        super(player);
        this.message = message;
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

    @Override
    public String message() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Set<User> recipients() {
        // TODO: Chat recipients module
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public void setRecipients(Set<User> recipients) {
        // TODO: Chat recipients module
        throw new VersionFeatureNotSupportedException();
    }
}
