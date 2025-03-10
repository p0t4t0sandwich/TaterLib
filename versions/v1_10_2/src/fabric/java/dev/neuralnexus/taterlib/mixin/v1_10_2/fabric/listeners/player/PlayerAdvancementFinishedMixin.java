/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_10_2.fabric.listeners.player;

import dev.neuralnexus.taterapi.event.api.PlayerEvents;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterlib.v1_10_2.vanilla.event.player.VanillaPlayerAdvancementEvent;

import net.minecraft.entity.living.player.PlayerEntity;
import net.minecraft.stat.Stat;
import net.minecraft.stat.achievement.AchievementStat;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@ReqMappings(Mappings.LEGACY_INTERMEDIARY)
@ReqMCVersion(min = MinecraftVersion.V9, max = MinecraftVersion.V9_4)
@Mixin(PlayerEntity.class)
public class PlayerAdvancementFinishedMixin {
    @Inject(method = "incrementStat(Lnet/minecraft/stat/Stat;)V", at = @At("HEAD"))
    public void onPlayerAdvancementFinished(Stat stat, CallbackInfo ci) {
        if (stat.isAchievement()) {
            PlayerEvents.ADVANCEMENT_FINISHED.invoke(
                    new VanillaPlayerAdvancementEvent.AdvancementFinished(
                            (PlayerEntity) (Object) this, (AchievementStat) stat));
        }
    }
}
