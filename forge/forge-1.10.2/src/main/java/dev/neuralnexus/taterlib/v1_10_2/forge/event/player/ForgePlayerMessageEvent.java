/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_10_2.forge.event.player;

import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.entity.player.SimplePlayer;
import dev.neuralnexus.taterapi.event.player.PlayerMessageEvent;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterlib.v1_10_2.forge.player.ForgePlayer;

import net.minecraftforge.event.ServerChatEvent;

import java.util.Set;

/** Forge implementation of {@link PlayerMessageEvent}. */
public class ForgePlayerMessageEvent implements PlayerMessageEvent {
    private final ServerChatEvent event;
    private String message = "";

    public ForgePlayerMessageEvent(ServerChatEvent event) {
        this.event = event;
    }

    @Override
    public boolean cancelled() {
        return event.isCanceled();
    }

    @Override
    public void setCancelled(boolean cancelled) {
        event.setCanceled(cancelled);
    }

    @Override
    public Player player() {
        return new ForgePlayer(event.getPlayer());
    }

    @Override
    public String message() {
        if (!this.message.isEmpty()) {
            return this.message;
        }
        return event.getMessage();
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Set<SimplePlayer> recipients() {
        // TODO: Chat recipients module
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public void setRecipients(Set<SimplePlayer> recipients) {
        // TODO: Chat recipients module
        throw new VersionFeatureNotSupportedException();
    }
}
