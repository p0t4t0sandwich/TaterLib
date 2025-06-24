/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_8_9.sponge.command;

import dev.neuralnexus.modapi.crossperms.PermsAPI;
import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.annotations.ToBeLibrary;
import dev.neuralnexus.taterapi.command.CommandSource;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.Notifiable;
import dev.neuralnexus.taterapi.entity.player.ServerPlayer;
import dev.neuralnexus.taterlib.v1_8_9.sponge.entity.SpongeEntity;
import dev.neuralnexus.taterlib.v1_8_9.sponge.entity.player.SpongePlayer;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import java.util.UUID;

/** Sponge implementation of {@link CommandSource} */
@ToBeLibrary("brigadier-general")
public class SpongeCommandSource
        implements CommandSource, Wrapped<org.spongepowered.api.command.CommandSource> {
    private final org.spongepowered.api.command.CommandSource sender;

    public SpongeCommandSource(org.spongepowered.api.command.CommandSource sender) {
        this.sender = sender;
    }

    @Override
    public org.spongepowered.api.command.CommandSource unwrap() {
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
        return message -> this.sender.sendMessage(Text.of(message));
    }

    @Override
    public @Nullable Entity actor() {
        if (this.sender instanceof org.spongepowered.api.entity.Entity) {
            return new SpongeEntity((org.spongepowered.api.entity.Entity) this.sender);
        }
        return null;
    }

    @Override
    public ServerPlayer subject() {
        if (this.sender instanceof Player) {
            return new SpongePlayer((Player) this.sender);
        }
        return null;
    }

    @Override
    public boolean isPlayer() {
        return this.sender instanceof Player;
    }

    @Override
    public void sendMessage(String message) {
        this.sender.sendMessage(Text.of(message));
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
