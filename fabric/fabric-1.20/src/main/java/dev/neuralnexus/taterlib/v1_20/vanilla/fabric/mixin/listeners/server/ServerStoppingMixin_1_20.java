package dev.neuralnexus.taterlib.v1_20.vanilla.fabric.mixin.listeners.server;

import dev.neuralnexus.taterlib.event.api.ServerEvents;
import dev.neuralnexus.taterlib.v1_20.vanilla.event.server.VanillaServerStoppingEvent;

import net.minecraft.server.MinecraftServer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Mixin for the server stopping listener. */
@Mixin(MinecraftServer.class)
public class ServerStoppingMixin_1_20 {
    /** Called when the server is stopping. */
    @Inject(at = @At("HEAD"), method = "stopServer")
    private void onServerStopping(CallbackInfo info) {
        ServerEvents.STOPPING.invoke(
                new VanillaServerStoppingEvent((MinecraftServer) (Object) this));
    }
}
