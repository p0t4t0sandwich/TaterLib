/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_17_1.forge.api.minecraft.commands;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.command.CommandSender;
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
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Interface.Remap;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.UUID;

@ReqMappings(Mappings.SEARGE)
@ReqMCVersion(min = MinecraftVersion.V17, max = MinecraftVersion.V20_6)
@Mixin(CommandSourceStack.class)
@Implements({
        @Interface(iface = CommandSender.class, prefix = "cmdSender$", remap = Remap.NONE),
        @Interface(iface = Identifiable.class, prefix = "identifiable$", remap = Remap.NONE),
        @Interface(iface = Nameable.class, prefix = "nameable$", remap = Remap.NONE),
        @Interface(iface = Notifiable.class, prefix = "notifiable$", remap = Remap.NONE),
})
public abstract class CommandSourceStack_API {
    // @spotless:off
    @Shadow public abstract String shadow$getTextName();
    @Shadow @Nullable public abstract net.minecraft.world.entity.Entity shadow$getEntity();
    // @spotless:on

    public @Nullable Entity cmdSender$getEntity() {
        if (this.shadow$getEntity() == null) {
            return null;
        }
        return (Entity) this.shadow$getEntity();
    }

    public String nameable$name() {
        return this.shadow$getTextName();
    }

    @SuppressWarnings("DataFlowIssue")
    public UUID identifiable$uuid() {
        if (this.shadow$getEntity() == null) {
            return TaterAPI.NIL_UUID;
        }
        return this.cmdSender$getEntity().uuid();
    }

    @Intrinsic
    public boolean identifiable$hasPermission(String permission) {
        return PermsAPI.instance().hasPermission(this, permission);
    }

    @Intrinsic
    public boolean identifiable$hasPermission(int permissionLevel) {
        return PermsAPI.instance().hasPermission(this, permissionLevel);
    }

    @Intrinsic
    public boolean identifiable$hasPermission(String permission, int permissionLevel) {
        return PermsAPI.instance().hasPermission(this, permission, permissionLevel);
    }
}
