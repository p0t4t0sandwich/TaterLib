/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_21.vanilla.listeners.server;

import com.mojang.brigadier.CommandDispatcher;

import dev.neuralnexus.conditionalmixins.annotations.ReqMCVersion;
import dev.neuralnexus.conditionalmixins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.Mappings;
import dev.neuralnexus.taterapi.MinecraftVersion;
import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterapi.event.api.CommandEvents;
import dev.neuralnexus.taterapi.event.api.ServerEvents;
import dev.neuralnexus.taterapi.event.server.impl.ServerStartingEventImpl;
import dev.neuralnexus.taterlib.v1_21.vanilla.event.command.VanillaBrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_21.vanilla.event.command.VanillaCommandRegisterEvent;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.MinecraftServer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Mixin for the server starting listener */
@ReqMappings(Mappings.MOJMAP)
@ReqMCVersion(min = MinecraftVersion.V1_21)
@Mixin(MinecraftServer.class)
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
        ServerEvents.STARTING.invoke(new ServerStartingEventImpl());

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
        if (!TaterAPIProvider.platform().isSpongeBased()) {
            CommandEvents.REGISTER_COMMAND.invoke(
                    new VanillaCommandRegisterEvent(dispatcher, commandSelection));
        }
    }
}
