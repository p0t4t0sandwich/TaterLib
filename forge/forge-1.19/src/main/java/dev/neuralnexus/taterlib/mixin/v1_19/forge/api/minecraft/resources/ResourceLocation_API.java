/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_19.forge.api.minecraft.resources;

import dev.neuralnexus.modapi.metadata.Mappings;
import dev.neuralnexus.modapi.metadata.enums.MinecraftVersion;
import dev.neuralnexus.modapi.metadata.enums.Platform;
import dev.neuralnexus.modapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.modapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.modapi.muxins.annotations.ReqPlatform;
import dev.neuralnexus.taterapi.resource.ResourceKey;

import net.minecraft.resources.ResourceLocation;

import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Interface.Remap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@ReqMappings(Mappings.SEARGE)
// Sponge's API satisfies the ResourceKey interface, and clashes with our default `asString` method
@ReqPlatform(not = Platform.SPONGE)
@ReqMCVersion(min = MinecraftVersion.V19, max = MinecraftVersion.V19_4)
@Mixin(ResourceLocation.class)
@Implements(@Interface(iface = ResourceKey.class, prefix = "resourceKey$", remap = Remap.NONE))
public abstract class ResourceLocation_API {
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
