/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_12_2.fabric.listeners.player;

import dev.neuralnexus.taterapi.event.api.PlayerEvents;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterlib.v1_12_2.vanilla.event.player.VanillaPlayerAdvancementEvent;

import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementProgress;
import net.minecraft.advancement.PlayerAdvancements;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.entity.living.player.ServerPlayerEntity;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@ReqMappings(Mappings.LEGACY_INTERMEDIARY)
@ReqMCVersion(min = MinecraftVersion.V12, max = MinecraftVersion.V12_2)
@Mixin(PlayerAdvancements.class)
public class PlayerAdvancementFinishedMixin {
    @Shadow private ServerPlayerEntity player;

    @Shadow @Final private MinecraftServer server;

    @Inject(method = "award", at = @At("HEAD"))
    public void onPlayerAdvancementFinished(
            Advancement advancement, String criterion, CallbackInfoReturnable<Boolean> cir) {
        PlayerEvents.ADVANCEMENT_PROGRESS.invoke(
                new VanillaPlayerAdvancementEvent.AdvancementProgress(
                        this.player, advancement, criterion));

        PlayerAdvancements playerAdvancements =
                this.server.getPlayerManager().getAdvancements(this.player);
        AdvancementProgress progress = playerAdvancements.getProgress(advancement);

        if (advancement.getInfo() != null
                && advancement.getInfo().showInChat()
                && progress.isComplete()) {
            PlayerEvents.ADVANCEMENT_FINISHED.invoke(
                    new VanillaPlayerAdvancementEvent.AdvancementFinished(
                            this.player, advancement));
        }
    }
}
