/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_19_1.forge.mixin.patch.command;

import dev.neuralnexus.conditionalmixins.annotations.ReqMCVersion;
import dev.neuralnexus.taterlib.api.MinecraftVersion;
import dev.neuralnexus.taterlib.v1_19.vanilla.command.VanillaCommandSender;

import net.minecraft.network.chat.Component;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

/** Patch for VanillaCommandSender 1.19.1. */
@ReqMCVersion(MinecraftVersion.V1_19_1)
@Mixin(value = VanillaCommandSender.class, remap = false)
public class VanillaCommandSenderMixin_1_19_1 {
    /**
     * @author Dylan Sperrer (p0t4t0sandwich)
     * @reason Patch for 1.19.1
     */
    @Overwrite
    public void sendMessage(String message) {
        ((VanillaCommandSender) (Object) this)
                .sender()
                .sendSystemMessage(Component.nullToEmpty(message));
    }
}
