/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.bungee.command;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.annotations.ToBeLibrary;
import dev.neuralnexus.taterapi.command.CommandSource;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.Notifiable;
import dev.neuralnexus.taterapi.entity.player.ProxyPlayer;
import dev.neuralnexus.taterapi.perms.PermsAPI;
import dev.neuralnexus.taterlib.bungee.entity.player.BungeePlayer;

import net.md_5.bungee.api.connection.ProxiedPlayer;

import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/** Bungee implementation of {@link CommandSource} */
@ToBeLibrary("brigadier-general")
public class BungeeCommandSource
        implements CommandSource, Wrapped<net.md_5.bungee.api.CommandSender> {
    private final net.md_5.bungee.api.CommandSender sender;

    public BungeeCommandSource(net.md_5.bungee.api.CommandSender sender) {
        this.sender = sender;
    }

    @Override
    public net.md_5.bungee.api.CommandSender unwrap() {
        return this.sender;
    }

    @Override
    public UUID uuid() {
        return TaterAPI.uuidFromName(this.sender.getName()).orElse(Notifiable.NIL_UUID);
    }

    @Override
    public String name() {
        if (this.player() != null) {
            return this.player().name();
        }
        return this.sender.getName();
    }

    @Override
    @SuppressWarnings("deprecation")
    public Notifiable source() {
        return this.sender::sendMessage;
    }

    @Override
    public @Nullable Entity actor() {
        return null;
    }

    @Override
    public @Nullable ProxyPlayer subject() {
        if (this.sender instanceof ProxiedPlayer) {
            return new BungeePlayer((ProxiedPlayer) this.sender);
        }
        return null;
    }

    @Override
    public boolean isPlayer() {
        return this.sender instanceof ProxiedPlayer;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void sendMessage(String message) {
        this.sender.sendMessage(message);
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
