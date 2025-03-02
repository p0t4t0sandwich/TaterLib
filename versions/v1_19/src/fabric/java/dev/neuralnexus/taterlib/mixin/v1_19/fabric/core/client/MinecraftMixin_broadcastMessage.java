/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_19.fabric.core.client;

import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.client.MinecraftBridge;

import net.minecraft.client.Minecraft;

import org.spongepowered.asm.mixin.Mixin;

@ReqMappings(Mappings.YARN_INTERMEDIARY)
@ReqMCVersion(min = MinecraftVersion.V19)
@Mixin(Minecraft.class)
public abstract class MinecraftMixin_broadcastMessage implements MinecraftBridge {
    @Override
    public void bridge$broadcastMessage(String message) {
        // TODO: Implement client chat sending for 1.19
        throw new VersionFeatureNotSupportedException();
    }
}
