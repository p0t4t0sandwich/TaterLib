/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_14_4.forge.api.minecraft.commands;

import dev.neuralnexus.taterapi.annotations.ToBeLibrary;
import dev.neuralnexus.taterapi.command.CommandSource;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.Identifiable;
import dev.neuralnexus.taterapi.entity.Nameable;
import dev.neuralnexus.taterapi.entity.Notifiable;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.perms.PermsAPI;

import net.minecraft.commands.CommandSourceStack;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Interface.Remap;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@ToBeLibrary("brigadier-general")
@ReqMappings(Mappings.LEGACY_SEARGE)
@ReqMCVersion(min = MinecraftVersion.V14, max = MinecraftVersion.V16_5)
@Mixin(CommandSourceStack.class)
@Implements({
    @Interface(iface = CommandSource.class, prefix = "source$", remap = Remap.NONE),
    @Interface(iface = Identifiable.class, prefix = "identifiable$", remap = Remap.NONE),
    @Interface(iface = Nameable.class, prefix = "nameable$", remap = Remap.NONE),
    @Interface(iface = Notifiable.class, prefix = "notifiable$", remap = Remap.NONE),
})
public abstract class CommandSourceStack_API {
    // @spotless:off
    @Shadow @Final private net.minecraft.commands.CommandSource source;
    @Shadow public abstract String shadow$getTextName();
    @Shadow @Nullable public abstract net.minecraft.world.entity.Entity shadow$getEntity();
    // @spotless:on

    public Notifiable source$getSource() {
        return (Notifiable) this.source;
    }

    public @Nullable Entity source$getEntity() {
        return (Entity) this.shadow$getEntity();
    }

    public String nameable$name() {
        return this.shadow$getTextName();
    }
}
