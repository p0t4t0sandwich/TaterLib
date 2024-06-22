package dev.neuralnexus.conditionalmixins;

import dev.neuralnexus.conditionalmixins.annotations.ReqMCVersion;
import dev.neuralnexus.conditionalmixins.annotations.ReqPlatform;
import dev.neuralnexus.taterlib.loader.api.MinecraftVersion;
import dev.neuralnexus.taterlib.loader.api.Platform;

import org.spongepowered.asm.mixin.Mixin;

@ReqMCVersion(
        min = MinecraftVersion.V1_17_1,
        max = MinecraftVersion.V1_18)
@ReqPlatform(Platform.FABRIC)
@Mixin(String.class)
public class TestMixin {}
