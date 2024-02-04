package dev.neuralnexus.taterlib.vanilla.mixin.listeners.server.minecraftserver;

import com.mojang.brigadier.CommandDispatcher;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.event.api.CommandEvents;
import dev.neuralnexus.taterlib.event.api.ServerEvents;
import dev.neuralnexus.taterlib.vanilla.event.command.VanillaBrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.vanilla.event.command.VanillaCommandRegisterEvent;
import dev.neuralnexus.taterlib.vanilla.event.server.VanillaServerStartedEvent;
import dev.neuralnexus.taterlib.vanilla.event.server.VanillaServerStartingEvent;
import dev.neuralnexus.taterlib.vanilla.event.server.VanillaServerStoppedEvent;
import dev.neuralnexus.taterlib.vanilla.event.server.VanillaServerStoppingEvent;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.MinecraftServer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Mixin for the server started listener */
@Mixin(MinecraftServer.class)
public class MinecraftServerMixin_1_20 {
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
    private void onServerStarting(CallbackInfo info) {
        // Fire the server starting event
        ServerEvents.STARTING.invoke(
                new VanillaServerStartingEvent((MinecraftServer) (Object) this));

        // Register Brigadier commands
        CommandDispatcher<CommandSourceStack> dispatcher =
                ((MinecraftServer) (Object) this)
                        .resources
                        .managers()
                        .getCommands()
                        .getDispatcher();
        Commands.CommandSelection commandSelection =
                ((MinecraftServer) (Object) this).isDedicatedServer()
                        ? Commands.CommandSelection.DEDICATED
                        : Commands.CommandSelection.INTEGRATED;

        CommandEvents.REGISTER_BRIGADIER_COMMAND.invoke(
                new VanillaBrigadierCommandRegisterEvent(dispatcher, commandSelection));

        // Sponge has its own, nicer simple command system
        if (!TaterAPIProvider.serverType().isSpongeBased()) {
            CommandEvents.REGISTER_COMMAND.invoke(
                    new VanillaCommandRegisterEvent(dispatcher, commandSelection));
        }
    }

    /** Called when the server has started. */
    @Inject(
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Lnet/minecraft/server/MinecraftServer;buildServerStatus()Lnet/minecraft/network/protocol/status/ServerStatus;",
                            ordinal = 0),
            method = "runServer")
    private void onServerStarted(CallbackInfo info) {
        ServerEvents.STARTED.invoke(new VanillaServerStartedEvent((MinecraftServer) (Object) this));
    }

    /** Called when the server is stopping. */
    @Inject(at = @At("HEAD"), method = "stopServer")
    private void onServerStopping(CallbackInfo info) {
        ServerEvents.STOPPING.invoke(
                new VanillaServerStoppingEvent((MinecraftServer) (Object) this));
    }

    /** Called when the server has stopped. */
    @Inject(at = @At("TAIL"), method = "stopServer")
    private void onServerStopped(CallbackInfo info) {
        ServerEvents.STOPPED.invoke(new VanillaServerStoppedEvent((MinecraftServer) (Object) this));
    }
}
