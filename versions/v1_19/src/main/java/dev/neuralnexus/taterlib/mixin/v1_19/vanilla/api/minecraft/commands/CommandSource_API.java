/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_19.vanilla.api.minecraft.commands;

import dev.neuralnexus.taterapi.annotations.ToBeLibrary;
import dev.neuralnexus.taterapi.entity.Notifiable;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.VanillaUtils;

import net.minecraft.commands.CommandSource;
import net.minecraft.network.chat.Component;

import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@ToBeLibrary("brigadier-general")
@ReqMappings(Mappings.MOJANG)
@ReqMCVersion(min = MinecraftVersion.V19)
@Mixin(CommandSource.class)
@Implements(
        @Interface(iface = Notifiable.class, prefix = "notifiable$", remap = Interface.Remap.NONE))
public interface CommandSource_API {
    // @spotless:off
    @Shadow void sendSystemMessage(Component message);
    // @spotless:on

    default void notifiable$sendMessage(String message) {
        this.sendSystemMessage(VanillaUtils.component.apply(message));
    }
}
