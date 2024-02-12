package dev.neuralnexus.taterlib.v1_19_3.vanilla.fabric.mixin.patch.player;

import dev.neuralnexus.taterlib.utils.Location;
import dev.neuralnexus.taterlib.v1_19.vanilla.player.VanillaPlayer;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

/** Patch mixin for VanillaPlayer 1.19.3. */
@Mixin(value = VanillaPlayer.class, remap = false)
public class VanillaPlayerMixin_1_19_3 {
    /**
     * @author Dylan Sperrer (p0t4t0sandwich)
     * @reason Patch for 1.19.3
     */
    @Overwrite
    public void setSpawn(Location location, boolean forced) {
        // TODO: Abstract world information
        ResourceLocation resourceLocation;
        String[] resourceString = location.world().split(":");
        if (resourceString.length != 1) {
            resourceLocation = new ResourceLocation(resourceString[0], resourceString[1]);
        } else {
            resourceLocation = new ResourceLocation(resourceString[0]);
        }
        ResourceKey<Level> dimension = ResourceKey.create(Registries.DIMENSION, resourceLocation);
        ((ServerPlayer) ((VanillaPlayer) (Object) (this)).player())
                .setRespawnPosition(
                        dimension,
                        new BlockPos((int) location.x(), (int) location.y(), (int) location.z()),
                        0,
                        forced,
                        false);
    }
}
