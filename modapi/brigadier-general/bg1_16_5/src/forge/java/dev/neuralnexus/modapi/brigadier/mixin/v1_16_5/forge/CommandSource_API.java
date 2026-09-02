/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.modapi.brigadier.mixin.v1_16_5.forge;

import dev.neuralnexus.taterapi.entity.Notifiable;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.anno.AConstraint;
import dev.neuralnexus.taterapi.meta.anno.Versions;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.network.chat.Component;

import net.minecraft.commands.CommandSource;

import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.UUID;

@AConstraint(
        mappings = Mappings.MOJANG,
        version = @Versions(min = MinecraftVersion.V16, max = MinecraftVersion.V16_5))
@Mixin(CommandSource.class)
@Implements(
        @Interface(iface = Notifiable.class, prefix = "notifiable$", remap = Interface.Remap.NONE))
public interface CommandSource_API {
    // @spotless:off
    @Shadow void sendMessage(net.minecraft.network.chat.Component message, UUID uuid);
    // @spotless:on

    default void notifiable$sendMessage(String message) {
        this.sendMessage(Component.literal(message), Notifiable.NIL_UUID);
    }
}
