/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_20.bukkit.event.player;

import dev.neuralnexus.taterapi.entity.player.SimplePlayer;
import dev.neuralnexus.taterapi.event.player.PlayerMessageEvent;
import dev.neuralnexus.taterlib.v1_20.bukkit.entity.player.BukkitPlayer;

import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Set;
import java.util.stream.Collectors;

/** Bukkit implementation of {@link PlayerMessageEvent}. */
@SuppressWarnings("deprecation")
public class BukkitPlayerMessageEvent extends BukkitPlayerEvent implements PlayerMessageEvent {
    private final AsyncPlayerChatEvent event;

    public BukkitPlayerMessageEvent(AsyncPlayerChatEvent event) {
        super(event);
        this.event = event;
    }

    @Override
    public boolean cancelled() {
        return this.event.isCancelled();
    }

    @Override
    public void setCancelled(boolean cancelled) {
        event.setCancelled(cancelled);
    }

    @Override
    public String message() {
        return this.event.getMessage();
    }

    @Override
    public void setMessage(String message) {
        this.event.setMessage(message);
    }

    @Override
    public Set<SimplePlayer> recipients() {
        return this.event.getRecipients().stream()
                .map(BukkitPlayer::new)
                .collect(Collectors.toSet());
    }

    @Override
    public void setRecipients(Set<SimplePlayer> recipients) {
        this.event.getRecipients().clear();
        this.event
                .getRecipients()
                .addAll(
                        recipients.stream()
                                .map(player -> ((BukkitPlayer) player).unwrap())
                                .collect(Collectors.toSet()));
    }
}
