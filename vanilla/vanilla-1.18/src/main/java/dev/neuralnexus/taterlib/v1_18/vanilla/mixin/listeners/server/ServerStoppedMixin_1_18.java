/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_18.vanilla.mixin.listeners.server;

import dev.neuralnexus.conditionalmixins.annotations.ReqMCVersion;
import dev.neuralnexus.taterlib.api.MinecraftVersion;
import dev.neuralnexus.taterlib.event.api.ServerEvents;
import dev.neuralnexus.taterlib.v1_18.vanilla.event.server.VanillaServerStoppedEvent;

import net.minecraft.server.MinecraftServer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Mixin for the server stopped listener. */
@ReqMCVersion(min = MinecraftVersion.V1_18, max = MinecraftVersion.V1_18_2)
@Mixin(MinecraftServer.class)
public class ServerStoppedMixin_1_18 {
    /** Called when the server has stopped. */
    @Inject(at = @At("TAIL"), method = "stopServer")
    private void onServerStopped(CallbackInfo info) {
        ServerEvents.STOPPED.invoke(new VanillaServerStoppedEvent((MinecraftServer) (Object) this));
    }
}
