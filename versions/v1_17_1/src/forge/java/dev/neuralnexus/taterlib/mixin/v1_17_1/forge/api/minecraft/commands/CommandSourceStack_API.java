/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_17_1.forge.api.minecraft.commands;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.command.CommandSender;
import dev.neuralnexus.taterapi.entity.Identifiable;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.perms.PermsAPI;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.commands.CommandSourceBridge;

import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.world.entity.Entity;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
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
    @Interface(iface = Identifiable.class, prefix = "identifiable$", remap = Remap.NONE)
})
public abstract class CommandSourceStack_API {
    @Shadow @Final private CommandSource source;

    @Shadow
    public abstract String shadow$getTextName();

    @Shadow
    @Nullable public abstract Entity shadow$getEntity();

    public String cmdSender$name() {
        return this.shadow$getTextName();
    }

    public void cmdSender$sendMessage(String message) {
        ((CommandSourceBridge) this.source).bridge$sendMessage(message);
    }

    @SuppressWarnings("DataFlowIssue")
    public UUID identifiable$uuid() {
        if (this.shadow$getEntity() == null) {
            return TaterAPI.uuidFromName(this.shadow$getTextName()).orElse(TaterAPI.NIL_UUID);
        }
        return this.shadow$getEntity().getUUID();
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
