/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_20_1.fabric.core.client;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.client.MinecraftBridge;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@ReqMappings(Mappings.YARN_INTERMEDIARY)
@ReqMCVersion(min = MinecraftVersion.V20, max = MinecraftVersion.V20_1)
@Mixin(Minecraft.class)
public abstract class MinecraftMixin_broadcastMessage implements MinecraftBridge {
    @Shadow @Nullable public LocalPlayer player;

    @Override
    public void bridge$broadcastMessage(String message) {
        if (this.player == null) return;
        this.player.connection.sendChat(message);
    }
}
