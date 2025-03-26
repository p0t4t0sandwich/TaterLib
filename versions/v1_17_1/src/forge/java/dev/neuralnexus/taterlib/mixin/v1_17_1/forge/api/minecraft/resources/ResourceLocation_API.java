/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_17_1.forge.api.minecraft.resources;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.meta.enums.Platform;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.muxins.annotations.ReqPlatform;
import dev.neuralnexus.taterapi.resource.ResourceKey;

import net.minecraft.resources.ResourceLocation;

import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Interface.Remap;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@ReqMappings(Mappings.SEARGE)
// Sponge's API satisfies the ResourceKey interface, and clashes with our default `asString` method
@ReqPlatform(not = Platform.SPONGE)
@ReqMCVersion(min = MinecraftVersion.V17, max = MinecraftVersion.V20_2)
@Mixin(ResourceLocation.class)
@Implements(@Interface(iface = ResourceKey.class, prefix = "resourceKey$", remap = Remap.NONE))
public abstract class ResourceLocation_API {
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
