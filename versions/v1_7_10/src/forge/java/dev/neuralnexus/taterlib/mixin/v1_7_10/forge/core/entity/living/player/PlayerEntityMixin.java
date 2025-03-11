/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_7_10.forge.core.entity.living.player;

import dev.neuralnexus.taterapi.TaterAPI;
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
import net.minecraft.server.ServerPlayerInteractionManager;
import net.minecraft.server.entity.living.player.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldSettings;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.lang.reflect.Method;
import java.util.Optional;

@ReqMappings(Mappings.LEGACY_SEARGE)
@ReqMCVersion(min = MinecraftVersion.V7_2, max = MinecraftVersion.V7_10)
@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin implements PlayerEntityBridge {
    @Shadow
    public abstract void shadow$setSpawnChunk(BlockPos pos, boolean forced, int dimensionId);

    @Shadow
    public abstract String shadow$getName();

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
                forced,
                serverLevel.get().dimension.id);
    }

    @Override
    @SuppressWarnings("DataFlowIssue")
    public GameMode bridge$gameMode() {
        // Reflect to access WorldSettings$GameType#getName() because GameType is private
        // TODO: Seems fine on Fabric for some odd reason
        try {
            return GameMode.fromName(
                    (String)
                            Class.forName("net.minecraft.world.WorldSettings$GameType")
                                    // func_77149_b is getName
                                    .getDeclaredMethod("func_77149_b")
                                    .invoke(
                                            ((ServerPlayerEntity) (Object) this)
                                                    .interactionManager.getGameMode()));
        } catch (Exception e) {
            TaterAPI.logger()
                    .error("Failed to get game mode for player " + this.shadow$getName(), e);
        }
        return GameMode.SURVIVAL;
    }

    @Override
    @SuppressWarnings("DataFlowIssue")
    public void bridge$setGameMode(GameMode gameMode) {
        // Reflect to access ItemInWorldManager#setGameType because GameType is private
        // TODO: Seems fine on Fabric for some odd reason
        try {
            Class<?> GameType = Class.forName("net.minecraft.world.WorldSettings$GameType");
            Object gameType = WorldSettings.getGameModeById(gameMode.id());
            ServerPlayerInteractionManager interactionManager =
                    ((ServerPlayerEntity) (Object) this).interactionManager;
            Method setGameMode =
                    interactionManager.getClass().getDeclaredMethod("func_73076_a", GameType);
            setGameMode.invoke(interactionManager, gameType);
        } catch (Exception e) {
            TaterAPI.logger()
                    .error("Failed to set game mode for player " + this.shadow$getName(), e);
        }
    }
}
