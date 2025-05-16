/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.velocity.v3_3_0.command;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.command.CommandSender;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.Notifiable;
import dev.neuralnexus.taterapi.entity.player.ProxyPlayer;
import dev.neuralnexus.taterapi.perms.PermsAPI;
import dev.neuralnexus.taterlib.velocity.v3_3_0.entity.player.VelocityPlayer;

import net.kyori.adventure.text.Component;

import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/** Velocity implementation of {@link CommandSender} */
public class VelocityCommandSender implements CommandSender, Wrapped<CommandSource> {
    private final CommandSource sender;

    public VelocityCommandSender(CommandSource sender) {
        this.sender = sender;
    }

    @Override
    public CommandSource unwrap() {
        return sender;
    }

    @Override
    public UUID uuid() {
        if (this.sender instanceof Player player) {
            return player.getUniqueId();
        }
        return TaterAPI.NIL_UUID;
    }

    @Override
    public String name() {
        if (this.getPlayer() != null) {
            return this.getPlayer().name();
        }
        return this.sender.getClass().getSimpleName();
    }

    @Override
    public Notifiable getSource() {
        return message -> this.sender.sendMessage(Component.text(message));
    }

    @Override
    public @Nullable Entity getEntity() {
        return null;
    }

    @Override
    public @Nullable ProxyPlayer getPlayer() {
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
