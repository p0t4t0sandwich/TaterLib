package dev.neuralnexus.taterlib.vanilla.fabric.mixin.listeners.server;

import dev.neuralnexus.taterlib.event.api.ServerEvents;
import dev.neuralnexus.taterlib.vanilla.event.server.VanillaServerStartingEvent;

import net.minecraft.server.MinecraftServer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Mixin for the server starting listener */
@Mixin(MinecraftServer.class)
public class ServerStartingMixin_1_20 {
    @Inject(
            method = "runServer",
            at =
                    @At(
                            value = "INVOKE",
                            target = "Lnet/minecraft/server/MinecraftServer;initServer()Z"))
    private void onServerStarting(CallbackInfo info) {
        ServerEvents.STARTING.invoke(
                new VanillaServerStartingEvent((MinecraftServer) (Object) this));
    }
}
