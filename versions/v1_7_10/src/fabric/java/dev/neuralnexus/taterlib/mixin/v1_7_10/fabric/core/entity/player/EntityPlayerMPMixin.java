/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_7_10.fabric.core.entity.player;

import dev.neuralnexus.taterapi.entity.player.GameMode;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.bridge.entity.player.EntityPlayerBridge;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.WorldSettings$GameType;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@ReqMappings(Mappings.LEGACY_INTERMEDIARY)
@ReqMCVersion(min = MinecraftVersion.V7_2, max = MinecraftVersion.V7_10)
@Mixin(EntityPlayer.class)
public abstract class EntityPlayerMPMixin implements EntityPlayerBridge {
    @Shadow
    public abstract String shadow$getCommandSenderName();

    @Shadow
    public abstract IChatComponent shadow$getFormattedCommandSenderName();

    @Shadow
    public abstract void shadow$setSpawnChunk(ChunkCoordinates pos, boolean forced);

    @Override
    public String bridge$name() {
        return this.shadow$getCommandSenderName();
    }

    @Override
    public String bridge$displayName() {
        return this.shadow$getFormattedCommandSenderName().getFormattedText();
    }

    @Override
    public void bridge$setSpawn(Location location, boolean forced) {
        this.shadow$setSpawnChunk(
                new ChunkCoordinates((int) location.x(), (int) location.y(), (int) location.z()),
                forced);
    }

    @Override
    @SuppressWarnings("DataFlowIssue")
    public GameMode bridge$gameMode() {
        WorldSettings$GameType gameType =
                (WorldSettings$GameType)
                        (Object)
                                ((EntityPlayerMP) (Object) this)
                                        .theItemInWorldManager.getGameType();
        return GameMode.fromName(gameType.getName());
    }
}
