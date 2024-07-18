/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_15.vanilla.mixin.accessors.network.protocol.game;

import dev.neuralnexus.conditionalmixins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.MinecraftVersion;

import net.minecraft.network.protocol.game.ServerboundCustomPayloadPacket;
import net.minecraft.resources.ResourceLocation;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/** Accessor for {@link ServerboundCustomPayloadPacket}. */
@ReqMCVersion(min = MinecraftVersion.V1_15, max = MinecraftVersion.V1_15_2)
@Mixin(ServerboundCustomPayloadPacket.class)
public interface ServerboundCustomPayloadPacketAccessor {
    @Accessor("identifier")
    ResourceLocation invoker$getIdentifier();
}
