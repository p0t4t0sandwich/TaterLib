package dev.neuralnexus.taterlib.mixin.v1_14_4.forge.core.world.entity;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterlib.mixin.v1_14_4.vanilla.accessors.world.entity.LivingEntityAccessor;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.world.entity.LivingEntityBridge;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;

@ReqMappings(Mappings.LEGACY_SEARGE)
@ReqMCVersion(min = MinecraftVersion.V14, max = MinecraftVersion.V15_2)
@Mixin(LivingEntity.class)
public class LivingEntityMixin implements LivingEntityBridge {
    @Override
    public int bridge$getExperienceReward(Player attackingPlayer) {
        return ((LivingEntityAccessor) this).invoker$getExperienceReward(attackingPlayer);
    }
}
