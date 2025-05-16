/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.bukkit.command;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.WrapperRegistry;
import dev.neuralnexus.taterapi.command.CommandSource;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.Notifiable;
import dev.neuralnexus.taterapi.entity.player.ServerPlayer;
import dev.neuralnexus.taterapi.perms.PermsAPI;

import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/** Bukkit implementation of {@link CommandSource} */
public class BukkitCommandSource
        implements CommandSource, Wrapped<org.bukkit.command.CommandSender> {
    private final org.bukkit.command.CommandSender sender;

    public BukkitCommandSource(org.bukkit.command.CommandSender sender) {
        this.sender = sender;
    }

    @Override
    public org.bukkit.command.CommandSender unwrap() {
        return this.sender;
    }

    @Override
    public UUID uuid() {
        return TaterAPI.uuidFromName(this.sender.getName()).orElse(TaterAPI.NIL_UUID);
    }

    @Override
    public String name() {
        if (this.getEntity() != null) {
            return this.getEntity().name();
        }
        return this.sender.getName();
    }

    @Override
    public Notifiable getSource() {
        return this.sender::sendMessage;
    }

    @Override
    public @Nullable Entity getEntity() {
        if (this.sender instanceof org.bukkit.entity.Entity) {
            return WrapperRegistry.wrap((org.bukkit.entity.Entity) this.sender);
        }
        return null;
    }

    @Override
    public ServerPlayer getPlayer() {
        if (this.sender instanceof org.bukkit.entity.Player) {
            return WrapperRegistry.wrap((org.bukkit.entity.Player) this.sender);
        }
        return null;
    }

    @Override
    public boolean isPlayer() {
        return this.sender instanceof org.bukkit.entity.Player;
    }

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
