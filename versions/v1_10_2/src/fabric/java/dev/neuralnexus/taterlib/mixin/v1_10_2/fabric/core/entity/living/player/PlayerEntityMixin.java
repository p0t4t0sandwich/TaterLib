/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_10_2.fabric.core.entity.living.player;

import dev.neuralnexus.taterapi.entity.player.GameMode;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.bridge.entity.living.player.PlayerEntityBridge;

import net.minecraft.entity.living.player.PlayerEntity;
import net.minecraft.server.entity.living.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldSettings;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@ReqMappings(Mappings.LEGACY_INTERMEDIARY)
@ReqMCVersion(min = MinecraftVersion.V10, max = MinecraftVersion.V12_2)
@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin implements PlayerEntityBridge {
    @Shadow
    public abstract void shadow$setSpawnPoint(BlockPos pos, boolean forced);

    @Override
    public void bridge$setSpawn(Location location, boolean forced) {
        this.shadow$setSpawnPoint(
                new BlockPos((int) location.x(), (int) location.y(), (int) location.z()), forced);
    }

    @Override
    @SuppressWarnings("DataFlowIssue")
    public GameMode bridge$gameMode() {
        net.minecraft.world.GameMode gameType =
                ((ServerPlayerEntity) (Object) this).interactionManager.getGameMode();
        return GameMode.fromName(gameType.getKey());
    }

    @Override
    @SuppressWarnings("DataFlowIssue")
    public void bridge$setGameMode(GameMode gameMode) {
        ((ServerPlayerEntity) (Object) this)
                .interactionManager.setGameMode(WorldSettings.getGameMode(gameMode.id()));
    }
}
