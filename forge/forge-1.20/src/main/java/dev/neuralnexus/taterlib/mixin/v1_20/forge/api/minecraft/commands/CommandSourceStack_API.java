/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_20.forge.api.minecraft.commands;

import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterapi.command.CommandSender;
import dev.neuralnexus.taterapi.entity.Identifiable;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Interface.Remap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.UUID;

@ReqMappings(Mappings.SEARGE)
@ReqMCVersion(min = MinecraftVersion.V20, max = MinecraftVersion.V20_6)
@Mixin(CommandSourceStack.class)
@Implements({
    @Interface(iface = CommandSender.class, prefix = "cmdSender$", remap = Remap.NONE),
    @Interface(iface = Identifiable.class, prefix = "identifiable$", remap = Remap.NONE)
})
public abstract class CommandSourceStack_API {
    @Shadow
    public abstract String shadow$getTextName();

    @Shadow
    public abstract void shadow$sendSystemMessage(Component message);

    @Shadow
    @Nullable public abstract Entity shadow$getEntity();

    public String cmdSender$name() {
        return this.shadow$getTextName();
    }

    public void cmdSender$sendMessage(String message) {
        this.shadow$sendSystemMessage(Component.nullToEmpty(message));
    }

    @SuppressWarnings("DataFlowIssue")
    public UUID identifiable$uuid() {
        if (this.shadow$getEntity() == null) {
            return TaterAPIProvider.uuidFromName(this.shadow$getTextName()).orElse(new UUID(0, 0));
        }
        return this.shadow$getEntity().getUUID();
    }
}
