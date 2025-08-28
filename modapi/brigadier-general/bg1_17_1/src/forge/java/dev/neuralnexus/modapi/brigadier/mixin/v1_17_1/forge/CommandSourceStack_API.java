/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.modapi.brigadier.mixin.v1_17_1.forge;

import dev.neuralnexus.taterapi.command.CommandSource;
import dev.neuralnexus.taterapi.entity.Actor;
import dev.neuralnexus.taterapi.entity.Notifiable;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;

import net.minecraft.commands.CommandSourceStack;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Interface.Remap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@ReqMappings(Mappings.SEARGE)
@ReqMCVersion(min = MinecraftVersion.V17)
@Mixin(CommandSourceStack.class)
@Implements(@Interface(iface = CommandSource.class, prefix = "source$", remap = Remap.NONE))
public abstract class CommandSourceStack_API {
    // @spotless:off
    @Shadow @Final private net.minecraft.commands.CommandSource source;
    @Shadow @Nullable public abstract net.minecraft.world.entity.Entity shadow$getEntity();
    // @spotless:on

    public Notifiable source$source() {
        return (Notifiable) this.source;
    }

    public @Nullable Actor source$entity() {
        return (Actor) this.shadow$getEntity();
    }
}
