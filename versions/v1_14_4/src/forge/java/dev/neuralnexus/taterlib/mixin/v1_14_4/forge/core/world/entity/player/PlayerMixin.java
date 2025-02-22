package dev.neuralnexus.taterlib.mixin.v1_14_4.forge.core.world.entity.player;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.world.entity.player.PlayerBridge;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.world.VanillaWorld;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;

import org.spongepowered.asm.mixin.Mixin;

@ReqMappings(Mappings.LEGACY_SEARGE)
@ReqMCVersion(min = MinecraftVersion.V14, max = MinecraftVersion.V14_4)
@Mixin(Player.class)
public class PlayerMixin implements PlayerBridge {
    @Override
    @SuppressWarnings("resource")
    public void bridge$setRespawnPosition(Location location, boolean forced) {
        ((Player) (Object) this).setSpawnPoint(new BlockPos(location.x(), location.y(), location.z()), forced, ((VanillaWorld) location.world()).unwrap().dimension.getType());
    }
}
