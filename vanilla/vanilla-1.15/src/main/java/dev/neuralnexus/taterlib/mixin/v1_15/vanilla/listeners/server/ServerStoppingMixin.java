/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_15.vanilla.listeners.server;

import dev.neuralnexus.taterapi.event.api.ServerEvents;
import dev.neuralnexus.taterapi.event.server.ServerStoppingEvent;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;

import net.minecraft.server.MinecraftServer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Mixin for the server stopping listener. */
@ReqMappings(Mappings.MOJANG)
@ReqMCVersion(min = MinecraftVersion.V15, max = MinecraftVersion.V15_2)
@Mixin(MinecraftServer.class)
@SuppressWarnings({"unused", "UnusedMixin"})
public class ServerStoppingMixin {
    /** Called when the server is stopping. */
    @Inject(at = @At("HEAD"), method = "stopServer")
    private void onServerStopping(CallbackInfo info) {
        ServerEvents.STOPPING.invoke(new ServerStoppingEvent() {});
    }
}
