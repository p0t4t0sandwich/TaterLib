package dev.neuralnexus.taterlib.v1_19_3.vanilla.mixin.patch.entity;

import dev.neuralnexus.taterlib.utils.Location;
import dev.neuralnexus.taterlib.v1_19.vanilla.entity.VanillaEntity;
import dev.neuralnexus.taterlib.v1_19.vanilla.server.VanillaServer;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

/** Patch mixin for VanillaEntity 1.19.3. */
@Mixin(value = VanillaEntity.class, remap = false)
public class VanillaEntityMixin_1_19_3 {
    /**
     * @author Dylan Sperrer (p0t4t0sandwich)
     * @reason Patch for 1.19.3
     */
    @Overwrite
    public void teleport(Location location) {
        VanillaEntity self = (VanillaEntity) (Object) this;
        Entity entity = self.getEntity();
        if (!location.getWorld().equals(self.getDimension())) {
            MinecraftServer server = VanillaServer.getServer();
            if (server == null) return;
            ResourceLocation resourceLocation;
            String[] resourceString = location.getWorld().split(":");
            if (resourceString.length != 1) {
                resourceLocation = new ResourceLocation(resourceString[0], resourceString[1]);
            } else {
                resourceLocation = new ResourceLocation(resourceString[0]);
            }
            ResourceKey<Level> dimension =
                    ResourceKey.create(Registries.DIMENSION, resourceLocation);
            ServerLevel serverLevel = server.getLevel(dimension);
            if (serverLevel == null) return;
            if (entity instanceof ServerPlayer player) {
                player.teleportTo(
                        serverLevel,
                        location.getX(),
                        location.getY(),
                        location.getZ(),
                        player.getYRot(),
                        player.getXRot());
                return;
            } else {
                entity.changeDimension(serverLevel);
            }
        }
        entity.teleportTo(location.getX(), location.getY(), location.getZ());
    }
}
