package dev.neuralnexus.taterlib.v1_19_3.vanilla.fabric.mixin.patch.player;

import dev.neuralnexus.taterlib.v1_19.vanilla.player.VanillaPlayer;
import dev.neuralnexus.taterlib.v1_19.vanilla.world.VanillaWorld;
import dev.neuralnexus.taterlib.world.Location;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

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
        ((ServerPlayer) ((VanillaPlayer) (Object) (this)).player())
                .setRespawnPosition(
                        ((VanillaWorld) location.world()).world().dimension(),
                        new BlockPos(location.x(), location.y(), location.z()),
                        0.0F,
                        forced,
                        false);
    }
}
