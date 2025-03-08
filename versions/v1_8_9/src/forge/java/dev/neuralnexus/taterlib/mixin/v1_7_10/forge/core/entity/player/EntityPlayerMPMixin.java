/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_7_10.forge.core.entity.player;

import dev.neuralnexus.taterapi.entity.player.GameMode;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.bridge.entity.player.EntityPlayerBridge;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.world.WrappedServerWorld;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldSettings;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@ReqMappings(Mappings.LEGACY_SEARGE)
@ReqMCVersion(min = MinecraftVersion.V8, max = MinecraftVersion.V8_9)
@Mixin(EntityPlayer.class)
public abstract class EntityPlayerMPMixin implements EntityPlayerBridge {
    @Shadow
    public abstract String shadow$getName();

    @Shadow
    public abstract String shadow$getDisplayNameString();

    @Shadow
    public abstract void shadow$setSpawnChunk(BlockPos pos, boolean forced, int dimensionId);

    @Override
    public String bridge$name() {
        return this.shadow$getName();
    }

    @Override
    public String bridge$displayName() {
        return this.shadow$getDisplayNameString();
    }

    @Override
    @SuppressWarnings("DataFlowIssue")
    public void bridge$setSpawn(Location location, boolean forced) {
        // TODO: Simplify once WrappedServerWorld is injected via mixin
        Optional<WorldServer> serverLevel =
                ((Server) ((EntityPlayerMP) (Object) this).mcServer)
                        .world(location.world().dimension())
                        .map(WrappedServerWorld.class::cast)
                        .map(WrappedServerWorld::unwrap);
        if (!serverLevel.isPresent()) return;
        this.shadow$setSpawnChunk(
                new BlockPos((int) location.x(), (int) location.y(), (int) location.z()),
                forced,
                serverLevel.get().provider.getDimensionId());
    }

    @Override
    @SuppressWarnings("DataFlowIssue")
    public GameMode bridge$gameMode() {
        WorldSettings.GameType gameType =
                ((EntityPlayerMP) (Object) this).theItemInWorldManager.getGameType();
        return GameMode.fromName(gameType.getName());
    }
}
