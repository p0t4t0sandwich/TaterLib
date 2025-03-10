/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_10_2.forge.api.minecraft.resource;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.resource.ResourceKey;

import net.minecraft.resource.Identifier;

import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Interface.Remap;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@ReqMappings(Mappings.LEGACY_SEARGE)
@ReqMCVersion(min = MinecraftVersion.V8, max = MinecraftVersion.V8_9)
@Mixin(Identifier.class)
@Implements(@Interface(iface = ResourceKey.class, prefix = "resourceKey$", remap = Remap.NONE))
public abstract class ResourceLocationAPI {
    @Shadow
    public abstract String shadow$getNamespace();

    @Shadow
    public abstract String shadow$getPath();

    @Intrinsic
    public String resourceKey$namespace() {
        return this.shadow$getNamespace();
    }

    @Intrinsic
    public String resourceKey$value() {
        return this.shadow$getPath();
    }
}
