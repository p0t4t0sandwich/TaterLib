/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_16_1.fabric.listeners.player;

import dev.neuralnexus.taterapi.event.api.PlayerEvents;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.event.player.VanillaPlayerRespawnEvent;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@ReqMappings(Mappings.YARN_INTERMEDIARY)
@ReqMCVersion(min = MinecraftVersion.V16, max = MinecraftVersion.V17_1)
@Mixin(PlayerList.class)
public class PlayerRespawnMixin {
    @Inject(method = "respawn", at = @At("HEAD"))
    public void onPlayerRespawn(
            ServerPlayer player, boolean alive, CallbackInfoReturnable<ServerPlayer> cir) {
        PlayerEvents.RESPAWN.invoke(new VanillaPlayerRespawnEvent(player, alive));
    }
}
