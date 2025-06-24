/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.vanilla.command;

import dev.neuralnexus.modapi.crossperms.PermsAPI;
import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.annotations.ToBeLibrary;
import dev.neuralnexus.taterapi.command.CommandSource;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.Notifiable;
import dev.neuralnexus.taterapi.entity.player.ServerPlayer;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.entity.WrappedEntity;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.entity.player.WrappedPlayer;

import net.minecraft.entity.living.player.PlayerEntity;
import net.minecraft.text.LiteralText;

import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/** Vanilla implementation of {@link CommandSource} */
@ToBeLibrary("brigadier-general")
public class WrappedCommandSource
        implements CommandSource, Wrapped<net.minecraft.server.command.source.CommandSource> {
    private final net.minecraft.server.command.source.CommandSource sender;

    public WrappedCommandSource(net.minecraft.server.command.source.CommandSource sender) {
        this.sender = sender;
    }

    @Override
    public net.minecraft.server.command.source.CommandSource unwrap() {
        return this.sender;
    }

    @Override
    public UUID uuid() {
        return TaterAPI.uuidFromName(this.sender.getName()).orElse(Notifiable.NIL_UUID);
    }

    @Override
    public String name() {
        return this.sender.getName();
    }

    @Override
    public Notifiable source() {
        return message -> this.sender.sendMessage(new LiteralText(message));
    }

    @Override
    public @Nullable Entity actor() {
        if (this.sender instanceof net.minecraft.entity.Entity) {
            return new WrappedEntity((net.minecraft.entity.Entity) this.sender);
        }
        return null;
    }

    @Override
    public ServerPlayer subject() {
        if (this.sender instanceof PlayerEntity) {
            return new WrappedPlayer((PlayerEntity) this.sender);
        }
        return null;
    }

    @Override
    public boolean isPlayer() {
        return this.sender instanceof PlayerEntity;
    }

    @Override
    public void sendMessage(String message) {
        this.sender.sendMessage(new LiteralText(message));
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
