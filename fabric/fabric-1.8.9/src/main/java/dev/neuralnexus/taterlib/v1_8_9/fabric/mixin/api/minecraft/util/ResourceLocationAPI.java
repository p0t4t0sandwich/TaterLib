/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_8_9.fabric.mixin.api.minecraft.util;

import dev.neuralnexus.conditionalmixins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.MinecraftVersion;
import dev.neuralnexus.taterapi.resource.ResourceKey;

import net.minecraft.util.Identifier;

import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Interface.Remap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@ReqMCVersion(min = MinecraftVersion.V1_8, max = MinecraftVersion.V1_8_9)
@Mixin(Identifier.class)
@Implements(
        value = {
            @Interface(iface = ResourceKey.class, prefix = "resourceKey$", remap = Remap.NONE)
        })
public abstract class ResourceLocationAPI {
    @Shadow
    public abstract String shadow$getNamespace();

    @Shadow
    public abstract String shadow$getPath();

    public String resourceKey$namespace() {
        return this.shadow$getNamespace();
    }

    public String resourceKey$value() {
        return this.shadow$getPath();
    }
}
