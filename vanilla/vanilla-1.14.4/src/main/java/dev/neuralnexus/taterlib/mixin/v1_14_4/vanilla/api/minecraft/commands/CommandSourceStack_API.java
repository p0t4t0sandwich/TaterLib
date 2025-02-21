/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_14_4.vanilla.api.minecraft.commands;

import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterapi.command.CommandSender;
import dev.neuralnexus.taterapi.entity.Identifiable;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;

import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.Entity;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Interface.Remap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.UUID;

@ReqMappings(Mappings.MOJANG)
@ReqMCVersion(min = MinecraftVersion.V14, max = MinecraftVersion.V14_4)
@Mixin(CommandSourceStack.class)
@Implements({
    @Interface(iface = CommandSender.class, prefix = "cmdSender$", remap = Remap.NONE),
    @Interface(iface = Identifiable.class, prefix = "identifiable$", remap = Remap.NONE)
})
@SuppressWarnings({"unused", "UnusedMixin"})
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
        this.source.sendMessage(new TextComponent(message));
    }

    @SuppressWarnings({"DataFlowIssue", "OptionalGetWithoutIsPresent"})
    public UUID identifiable$uuid() {
        if (this.shadow$getEntity() == null) {
            return TaterAPIProvider.uuidFromName(this.shadow$getTextName()).orElse(new UUID(0, 0));
        }
        return this.shadow$getEntity().getUUID();
    }
}
