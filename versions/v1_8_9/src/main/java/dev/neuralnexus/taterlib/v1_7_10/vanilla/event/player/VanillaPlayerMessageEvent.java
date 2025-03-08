/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.vanilla.event.player;

import dev.neuralnexus.taterapi.entity.player.User;
import dev.neuralnexus.taterapi.event.Cancellable;
import dev.neuralnexus.taterapi.event.player.PlayerMessageEvent;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;

import net.minecraft.entity.player.EntityPlayer;

import java.util.Set;

/** Vanilla implementation of {@link PlayerMessageEvent}. */
public class VanillaPlayerMessageEvent extends VanillaPlayerEvent implements PlayerMessageEvent {
    private final Cancellable cancel;
    private final String message;

    public VanillaPlayerMessageEvent(EntityPlayer player, String message, Cancellable cancel) {
        super(player);
        this.message = message;
        this.cancel = cancel;
    }

    @Override
    public boolean cancelled() {
        return cancel.cancelled();
    }

    @Override
    public void setCancelled(boolean cancelled) {
        cancel.setCancelled(cancelled);
    }

    @Override
    public String message() {
        return this.message;
    }

    // TODO: Create message override system (mixin)
    @Override
    public void setMessage(String message) {
        throw new VersionFeatureNotSupportedException();
    }

    // TODO: Chat recipients module
    @Override
    public Set<User> recipients() {
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public void setRecipients(Set<User> recipients) {
        throw new VersionFeatureNotSupportedException();
    }
}
