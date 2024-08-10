/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.api.mojang.authlib;

import com.mojang.authlib.GameProfile;

import dev.neuralnexus.conditionalmixins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.MinecraftVersion;
import dev.neuralnexus.taterapi.command.CommandSender;
import dev.neuralnexus.taterapi.entity.Permissible;
import dev.neuralnexus.taterapi.entity.player.SimplePlayer;

import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Interface.Remap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.UUID;

@ReqMCVersion(min = MinecraftVersion.V1_14)
@Mixin(value = GameProfile.class, remap = false)
@Implements({
    @Interface(iface = CommandSender.class, prefix = "cmdSender$", remap = Remap.NONE),
    @Interface(iface = Permissible.class, prefix = "permissible$", remap = Remap.NONE),
    @Interface(iface = SimplePlayer.class, prefix = "simplePlayer$", remap = Remap.NONE)
})
public abstract class GameProfile_API {
    @Shadow
    public abstract String shadow$getName();

    @Shadow
    public abstract UUID shadow$getId();

    public String cmdSender$name() {
        return this.shadow$getName();
    }

    public void cmdSender$sendMessage(String message) {
        // TODO: Some abstract way to wrap around and send a message.
        //   Needs to be as such in order to keep this class platform-agnostic.
        //   Might update SimplePlayer to send messages to a specific player.
        //   That way TaterAPIProvider can be used to dispatch the message.
    }

    public String simplePlayer$displayName() {
        return this.cmdSender$name();
    }

    public UUID permissible$uuid() {
        return this.shadow$getId();
    }

    public boolean permissible$hasPermission(int permissionLevel) {
        return false;
    }
}
