/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.velocity.v3_3_0.command;

import com.velocitypowered.api.proxy.Player;

import dev.neuralnexus.modapi.crossperms.PermsAPI;
import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.annotations.ToBeLibrary;
import dev.neuralnexus.taterapi.command.CommandSource;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.Notifiable;
import dev.neuralnexus.taterapi.entity.player.ProxyPlayer;
import dev.neuralnexus.taterlib.velocity.v3_3_0.entity.player.VelocityPlayer;

import net.kyori.adventure.text.Component;

import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/** Velocity implementation of {@link CommandSource} */
@ToBeLibrary("brigadier-general")
public class VelocityCommandSource
        implements CommandSource, Wrapped<com.velocitypowered.api.command.CommandSource> {
    private final com.velocitypowered.api.command.CommandSource sender;

    public VelocityCommandSource(com.velocitypowered.api.command.CommandSource sender) {
        this.sender = sender;
    }

    @Override
    public com.velocitypowered.api.command.CommandSource unwrap() {
        return sender;
    }

    @Override
    public UUID uuid() {
        if (this.sender instanceof Player player) {
            return player.getUniqueId();
        }
        return Notifiable.NIL_UUID;
    }

    @Override
    public String name() {
        if (this.player() != null) {
            return this.player().name();
        }
        return this.sender.getClass().getSimpleName();
    }

    @Override
    public Notifiable source() {
        return message -> this.sender.sendMessage(Component.text(message));
    }

    @Override
    public @Nullable Entity actor() {
        return null;
    }

    @Override
    public @Nullable ProxyPlayer subject() {
        if (this.sender instanceof Player) {
            return new VelocityPlayer((Player) this.sender);
        }
        return null;
    }

    @Override
    public boolean isPlayer() {
        return this.sender instanceof Player;
    }

    @Override
    public void sendMessage(String message) {
        sender.sendMessage(Component.text(message));
    }

    @Override
    public boolean hasPermission(String permission) {
        return PermsAPI.instance().hasPermission(this, permission);
    }

    @Override
    public boolean hasPermission(int permissionLevel) {
        return PermsAPI.instance().hasPermission(this, permissionLevel);
    }

    @Override
    public boolean hasPermission(String permission, int permissionLevel) {
        return PermsAPI.instance().hasPermission(this, permission, permissionLevel);
    }
}
