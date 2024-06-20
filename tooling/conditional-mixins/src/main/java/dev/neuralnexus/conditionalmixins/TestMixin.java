package dev.neuralnexus.conditionalmixins;

import dev.neuralnexus.conditionalmixins.annotations.ReqServerType;
import dev.neuralnexus.conditionalmixins.api.ServerType;
import org.spongepowered.asm.mixin.Mixin;

@ReqServerType(ServerType.BUKKIT)
@Mixin(String.class)
public class TestMixin {}
