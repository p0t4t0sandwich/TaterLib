package dev.neuralnexus.taterlib.v1_20.vanilla.fabric.mixin.listeners.server;

import dev.neuralnexus.taterlib.event.api.ServerEvents;
import dev.neuralnexus.taterlib.v1_20.vanilla.event.server.VanillaServerStartedEvent;

import net.minecraft.server.MinecraftServer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Mixin for the server started listener. */
@Mixin(MinecraftServer.class)
public class ServerStartedMixin_1_20 {
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
}
