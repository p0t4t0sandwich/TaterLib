/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_19_3.forge.mixin.patch.entity;

import dev.neuralnexus.conditionalmixins.annotations.ReqMCVersion;
import dev.neuralnexus.taterlib.api.MinecraftVersion;
import dev.neuralnexus.taterlib.v1_19.vanilla.entity.VanillaEntity;
import dev.neuralnexus.taterlib.v1_19.vanilla.server.VanillaServer;
import dev.neuralnexus.taterlib.v1_19.vanilla.world.VanillaServerWorld;
import dev.neuralnexus.taterlib.world.Location;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Optional;

/** Patch mixin for VanillaEntity 1.19.3. */
@ReqMCVersion(MinecraftVersion.V1_19_3)
@Mixin(value = VanillaEntity.class, remap = false)
public class VanillaEntityMixin_1_19_3 {
    /**
     * @author Dylan Sperrer (p0t4t0sandwich)
     * @reason Patch for 1.19.3
     */
    @Overwrite
    public void teleport(Location location) {
        VanillaEntity self = (VanillaEntity) (Object) this;
        Entity entity = self.entity();
        if (!location.world().dimension().equals(self.dimension())) {
            Optional<ServerLevel> serverLevel =
                    VanillaServer.instance()
                            .world(location.world().dimension())
                            .map(VanillaServerWorld.class::cast)
                            .map(VanillaServerWorld::world);
            if (serverLevel.isEmpty()) return;
            if (entity instanceof ServerPlayer player) {
                player.teleportTo(
                        serverLevel.get(),
                        location.x(),
                        location.y(),
                        location.z(),
                        player.getYRot(),
                        player.getXRot());
                return;
            } else {
                entity.changeDimension(serverLevel.get());
            }
        }
        entity.teleportTo(location.x(), location.y(), location.z());
    }
}
