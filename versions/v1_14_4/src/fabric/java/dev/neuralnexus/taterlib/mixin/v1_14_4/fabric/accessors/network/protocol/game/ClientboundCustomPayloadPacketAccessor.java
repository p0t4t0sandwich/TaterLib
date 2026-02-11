/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_14_4.fabric.accessors.network.protocol.game;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.anno.AConstraint;
import dev.neuralnexus.taterapi.meta.anno.Versions;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundCustomPayloadPacket;
import net.minecraft.resources.ResourceLocation;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@AConstraint(
        mappings = Mappings.YARN_INTERMEDIARY,
        version = @Versions(min = MinecraftVersion.V14, max = MinecraftVersion.V20_1))
@Mixin(ClientboundCustomPayloadPacket.class)
public interface ClientboundCustomPayloadPacketAccessor {
    @Accessor("identifier")
    ResourceLocation accessor$identifier();

    @Accessor("data")
    FriendlyByteBuf accessor$data();
}
