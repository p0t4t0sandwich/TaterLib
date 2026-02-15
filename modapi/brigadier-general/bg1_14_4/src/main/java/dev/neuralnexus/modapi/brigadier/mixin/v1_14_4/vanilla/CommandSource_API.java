/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.modapi.brigadier.mixin.v1_14_4.vanilla;

import dev.neuralnexus.taterapi.entity.Notifiable;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.anno.AConstraint;
import dev.neuralnexus.taterapi.meta.anno.Versions;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;

import dev.neuralnexus.taterapi.network.chat.Component;
import net.minecraft.commands.CommandSource;

import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Interface.Remap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@AConstraint(
        mappings = Mappings.LEGACY_SEARGE,
        version = @Versions(min = MinecraftVersion.V14, max = MinecraftVersion.V15_2))
@Mixin(CommandSource.class)
@Implements(@Interface(iface = Notifiable.class, prefix = "notifiable$", remap = Remap.NONE))
public interface CommandSource_API {
    // @spotless:off
    @Shadow void sendMessage(net.minecraft.network.chat.Component message);
    // @spotless:on

    default void notifiable$sendMessage(String message) {
        this.sendMessage(Component.literal(message));
    }
}
