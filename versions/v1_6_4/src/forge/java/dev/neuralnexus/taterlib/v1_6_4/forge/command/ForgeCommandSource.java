/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_6_4.forge.command;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.annotations.ToBeLibrary;
import dev.neuralnexus.taterapi.command.CommandSource;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.Notifiable;
import dev.neuralnexus.taterapi.entity.player.ServerPlayer;
import dev.neuralnexus.taterapi.perms.PermsAPI;
import dev.neuralnexus.taterlib.v1_6_4.forge.entity.ForgeEntity;
import dev.neuralnexus.taterlib.v1_6_4.forge.entity.player.ForgePlayer;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatMessageComponent;

import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/** Forge implementation of {@link CommandSource} */
@ToBeLibrary("brigadier-general")
public class ForgeCommandSource implements CommandSource, Wrapped<ICommandSender> {
    private final ICommandSender sender;

    public ForgeCommandSource(ICommandSender sender) {
        this.sender = sender;
    }

    @Override
    public ICommandSender unwrap() {
        return this.sender;
    }

    @Override
    public UUID uuid() {
        return TaterAPI.uuidFromName(this.sender.getCommandSenderName())
                .orElse(Notifiable.NIL_UUID);
    }

    @Override
    public String name() {
        return this.sender.getCommandSenderName();
    }

    @Override
    public Notifiable source() {
        return message ->
                this.sender.sendChatToPlayer(ChatMessageComponent.createFromText(message));
    }

    @Override
    public @Nullable Entity actor() {
        if (this.sender instanceof net.minecraft.entity.Entity) {
            return new ForgeEntity((net.minecraft.entity.Entity) this.sender);
        }
        return null;
    }

    @Override
    public ServerPlayer subject() {
        if (this.sender instanceof EntityPlayer) {
            return new ForgePlayer((EntityPlayer) this.sender);
        }
        return null;
    }

    @Override
    public boolean isPlayer() {
        return this.sender instanceof EntityPlayer;
    }

    @Override
    public void sendMessage(String message) {
        this.sender.sendChatToPlayer(ChatMessageComponent.createFromText(message));
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
