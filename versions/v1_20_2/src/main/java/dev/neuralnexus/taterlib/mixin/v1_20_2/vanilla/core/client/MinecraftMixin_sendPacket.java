/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_20_2.vanilla.core.client;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.anno.AConstraint;
import dev.neuralnexus.taterapi.meta.anno.Versions;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.network.protocol.Packet;
import dev.neuralnexus.taterlib.network.ConnectionBridge;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.client.MinecraftBridge;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@AConstraint(
        mappings = Mappings.MOJANG,
        version = @Versions(min = MinecraftVersion.V20_2, max = MinecraftVersion.V20_4))
@Mixin(Minecraft.class)
public abstract class MinecraftMixin_sendPacket implements MinecraftBridge {
    @Shadow @Nullable public LocalPlayer player;

    @Override
    public void bridge$sendPacket(final @NonNull Packet packet) {
        if (this.player == null) return; // TODO: Create fallback
        ((ConnectionBridge) this.player.connection).bridge$send(packet);
    }
}
