/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_19.vanilla.listeners.server;

import com.mojang.brigadier.CommandDispatcher;

import dev.neuralnexus.taterapi.event.api.CommandEvents;
import dev.neuralnexus.taterapi.event.api.ServerEvents;
import dev.neuralnexus.taterapi.event.server.ServerStartingEvent;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.Platforms;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterlib.v1_19.vanilla.event.command.VanillaBrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_19.vanilla.event.command.VanillaCommandRegisterEvent;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.MinecraftServer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Mixin for the server starting listener */
@ReqMappings(Mappings.MOJANG)
@ReqMCVersion(min = MinecraftVersion.V19, max = MinecraftVersion.V19_4)
@Mixin(MinecraftServer.class)
@SuppressWarnings({"unused", "UnusedMixin"})
public class ServerStartingMixin {
    /**
     * Called when the server is starting. <br>
     * Side Effect: Triggers command registration events
     */
    @Inject(
            method = "runServer",
            at =
                    @At(
                            value = "INVOKE",
                            target = "Lnet/minecraft/server/MinecraftServer;initServer()Z"))
    @SuppressWarnings("DataFlowIssue")
    private void onServerStarting(CallbackInfo info) {
        // Fire the server starting event
        ServerEvents.STARTING.invoke(new ServerStartingEvent() {});

        // Register Brigadier commands
        CommandDispatcher<CommandSourceStack> dispatcher =
                ((MinecraftServer) (Object) this).getCommands().getDispatcher();
        Commands.CommandSelection commandSelection =
                ((MinecraftServer) (Object) this).isDedicatedServer()
                        ? Commands.CommandSelection.DEDICATED
                        : Commands.CommandSelection.INTEGRATED;

        CommandEvents.REGISTER_BRIGADIER_COMMAND.invoke(
                new VanillaBrigadierCommandRegisterEvent(dispatcher, commandSelection));

        // Sponge has its own, nicer simple command system
        if (!MetaAPI.instance().isPlatformPresent(Platforms.SPONGE)) {
            CommandEvents.REGISTER_COMMAND.invoke(
                    new VanillaCommandRegisterEvent(dispatcher, commandSelection));
        }
    }
}
