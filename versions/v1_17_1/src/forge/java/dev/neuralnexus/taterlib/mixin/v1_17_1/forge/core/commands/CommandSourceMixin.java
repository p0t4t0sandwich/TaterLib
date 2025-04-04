/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_17_1.forge.core.commands;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.commands.CommandSourceBridge;

import net.minecraft.Util;
import net.minecraft.commands.CommandSource;
import net.minecraft.network.chat.Component;

import org.spongepowered.asm.mixin.Mixin;

@ReqMappings(Mappings.SEARGE)
@ReqMCVersion(min = MinecraftVersion.V17, max = MinecraftVersion.V17_1)
@Mixin(CommandSource.class)
public interface CommandSourceMixin extends CommandSourceBridge {
    @Override
    default void bridge$sendMessage(String message) {
        ((CommandSource) this).sendMessage(Component.nullToEmpty(message), Util.NIL_UUID);
    }
}
