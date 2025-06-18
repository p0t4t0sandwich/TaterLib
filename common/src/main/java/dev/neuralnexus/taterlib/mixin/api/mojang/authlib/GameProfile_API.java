/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.api.mojang.authlib;

import com.mojang.authlib.GameProfile;

import dev.neuralnexus.taterapi.annotations.ToBeLibrary;
import dev.neuralnexus.taterapi.entity.Identifiable;
import dev.neuralnexus.taterapi.entity.Nameable;
import dev.neuralnexus.taterapi.entity.Notifiable;
import dev.neuralnexus.taterapi.entity.player.User;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.modapi.crossperms.PermsAPI;

import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Interface.Remap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.UUID;

@ReqMCVersion(min = MinecraftVersion.V14)
@Mixin(value = GameProfile.class, remap = false)
@Implements({
    @Interface(iface = Identifiable.class, prefix = "identifiable$", remap = Remap.NONE),
    @Interface(iface = Nameable.class, prefix = "nameable$", remap = Remap.NONE),
    @Interface(iface = Notifiable.class, prefix = "notifiable$", remap = Remap.NONE),
    @Interface(iface = User.class, prefix = "user$", remap = Remap.NONE)
})
public abstract class GameProfile_API {
    // @spotless:off
    @Shadow public abstract String shadow$getName();
    @Shadow public abstract UUID shadow$getId();
    // @spotless:on

    @ToBeLibrary("brigadier-general")
    public String nameable$name() {
        return this.shadow$getName();
    }

    @ToBeLibrary("crossperms")
    public UUID identifiable$uuid() {
        return this.shadow$getId();
    }

    @ToBeLibrary("brigadier-general")
    public void notifiable$sendMessage(String message) {
        // TODO: Some abstract way to wrap around and send a message.
        //   Needs to be as such in order to keep this class platform-agnostic.
        //   Might update SimplePlayer to send messages to a specific player.
        //   That way TaterAPIProvider can be used to dispatch the message.
    }

    @ToBeLibrary("crossperms")
    public boolean identifiable$hasPermission(String permission) {
        return PermsAPI.instance().hasPermission(this, permission);
    }

    @ToBeLibrary("crossperms")
    public boolean identifiable$hasPermission(int permissionLevel) {
        return PermsAPI.instance().hasPermission(this, permissionLevel);
    }

    @ToBeLibrary("crossperms")
    public boolean identifiable$hasPermission(String permission, int defaultPermissionLevel) {
        return PermsAPI.instance().hasPermission(this, permission, defaultPermissionLevel);
    }
}
