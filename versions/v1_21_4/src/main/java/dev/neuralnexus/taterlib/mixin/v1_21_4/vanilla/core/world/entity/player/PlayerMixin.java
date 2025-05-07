package dev.neuralnexus.taterlib.mixin.v1_21_4.vanilla.core.world.entity.player;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.VanillaUtils;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.world.entity.player.PlayerBridge;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

import org.spongepowered.asm.mixin.Mixin;

@ReqMappings(Mappings.MOJANG)
@ReqMCVersion(min = MinecraftVersion.V21_4)
@Mixin(Player.class)
public abstract class PlayerMixin implements PlayerBridge {
    @SuppressWarnings("ConstantValue")
    @Override
    public void bridge$sendMessage(String message) {
        if ((Object) this instanceof ServerPlayer player) {
            player.sendSystemMessage(VanillaBootstrap.componentFactory.apply(message));
        }
    }
}
