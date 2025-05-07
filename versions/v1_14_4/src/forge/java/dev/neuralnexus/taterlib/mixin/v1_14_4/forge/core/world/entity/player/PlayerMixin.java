package dev.neuralnexus.taterlib.mixin.v1_14_4.forge.core.world.entity.player;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.VanillaUtils;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.world.entity.player.PlayerBridge;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import org.spongepowered.asm.mixin.Mixin;

@ReqMappings(Mappings.LEGACY_SEARGE)
@ReqMCVersion(min = MinecraftVersion.V14, max = MinecraftVersion.V15_2)
@Mixin(Player.class)
public abstract class PlayerMixin extends Entity implements PlayerBridge {
    public PlayerMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void bridge$sendMessage(String message) {
        this.sendMessage(VanillaBootstrap.componentFactory.apply(message));
    }
}
