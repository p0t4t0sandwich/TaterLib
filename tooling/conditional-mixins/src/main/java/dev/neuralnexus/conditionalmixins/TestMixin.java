/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca The project is Licensed under <a
 * href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a> The API is Licensed
 * under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.conditionalmixins;

import dev.neuralnexus.conditionalmixins.annotations.ReqMCVersion;
import dev.neuralnexus.conditionalmixins.annotations.ReqPlatform;
import dev.neuralnexus.taterlib.api.MinecraftVersion;
import dev.neuralnexus.taterlib.api.Platform;

import org.spongepowered.asm.mixin.Mixin;

@ReqMCVersion(min = MinecraftVersion.V1_17_1, max = MinecraftVersion.V1_18)
@ReqPlatform(Platform.FABRIC)
@Mixin(String.class)
public class TestMixin {}
