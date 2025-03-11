/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_10_2.forge.core.entity.living.player;

import dev.neuralnexus.taterapi.entity.player.GameMode;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.bridge.entity.living.player.PlayerEntityBridge;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.world.WrappedServerWorld;

import net.minecraft.entity.living.player.PlayerEntity;
import net.minecraft.server.entity.living.player.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldSettings;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@ReqMappings(Mappings.LEGACY_SEARGE)
@ReqMCVersion(min = MinecraftVersion.V10, max = MinecraftVersion.V11_2)
@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin implements PlayerEntityBridge {
    @Shadow
    public abstract void shadow$setSpawnChunk(BlockPos pos, boolean forced, int dimensionId);

    @Override
    @SuppressWarnings("DataFlowIssue")
    public void bridge$setSpawn(Location location, boolean forced) {
        // TODO: Simplify once WrappedServerWorld is injected via mixin
        Optional<ServerWorld> serverLevel =
                ((Server) ((ServerPlayerEntity) (Object) this).server)
                        .world(location.world().dimension())
                        .map(WrappedServerWorld.class::cast)
                        .map(WrappedServerWorld::unwrap);
        if (!serverLevel.isPresent()) return;
        this.shadow$setSpawnChunk(
                new BlockPos((int) location.x(), (int) location.y(), (int) location.z()),
                forced, // TODO: Create a helper to convert the world to a dimId?
                serverLevel.get().dimension.getType().getId());
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
