/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.bungee.command;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.command.CommandSender;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.player.ProxyPlayer;
import dev.neuralnexus.taterapi.perms.PermsAPI;
import dev.neuralnexus.taterlib.bungee.entity.player.BungeePlayer;

import net.md_5.bungee.api.connection.ProxiedPlayer;

import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/** Bungee implementation of {@link CommandSender} */
public class BungeeCommandSender
        implements CommandSender, Wrapped<net.md_5.bungee.api.CommandSender> {
    private final net.md_5.bungee.api.CommandSender sender;

    public BungeeCommandSender(net.md_5.bungee.api.CommandSender sender) {
        this.sender = sender;
    }

    @Override
    public net.md_5.bungee.api.CommandSender unwrap() {
        return this.sender;
    }

    @Override
    public UUID uuid() {
        return TaterAPI.uuidFromName(this.sender.getName()).orElse(TaterAPI.NIL_UUID);
    }

    @Override
    public String name() {
        return this.sender.getName();
    }

    @Override
    public @Nullable Entity getEntity() {
        return null;
    }

    @Override
    public @Nullable ProxyPlayer getPlayer() {
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
