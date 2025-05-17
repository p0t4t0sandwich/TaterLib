/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_13_2.forge.command;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.command.CommandSource;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.Notifiable;
import dev.neuralnexus.taterapi.entity.player.ServerPlayer;
import dev.neuralnexus.taterapi.perms.PermsAPI;
import dev.neuralnexus.taterlib.v1_13_2.forge.entity.ForgeEntity;
import dev.neuralnexus.taterlib.v1_13_2.forge.entity.player.ForgePlayer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;

import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/** Forge implementation of {@link CommandSource} */
public class ForgeCommandSource
        implements CommandSource, Wrapped<net.minecraft.command.CommandSource> {
    private final net.minecraft.command.CommandSource sender;

    public ForgeCommandSource(net.minecraft.command.CommandSource sender) {
        this.sender = sender;
    }

    @Override
    public net.minecraft.command.CommandSource unwrap() {
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
        return message -> this.sender.sendFeedback(new TextComponentString(message), false);
    }

    @Override
    public @Nullable Entity entity() {
        if (this.sender.getEntity() != null) {
            return new ForgeEntity(this.sender.getEntity());
        }
        return null;
    }

    @Override
    public ServerPlayer player() {
        if (this.sender.getEntity() instanceof EntityPlayer) {
            return new ForgePlayer((EntityPlayer) this.sender.getEntity());
        }
        return null;
    }

    @Override
    public boolean isPlayer() {
        return this.sender.getEntity() instanceof EntityPlayer;
    }

    @Override
    public void sendMessage(String message) {
        this.sender.sendFeedback(new TextComponentString(message), false);
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
