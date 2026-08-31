/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_14_4.fabric.api.minecraft.resources;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.anno.AConstraint;
import dev.neuralnexus.taterapi.meta.anno.AConstraints;
import dev.neuralnexus.taterapi.meta.anno.Versions;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.meta.enums.Platform;
import dev.neuralnexus.taterapi.resources.Identifier;

import net.minecraft.resources.ResourceLocation;

import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Interface.Remap;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

// Sponge's API satisfies the ResourceKey interface, and clashes with our default `asString` method
@AConstraints({
    @AConstraint(platform = Platform.SPONGE, invert = true),
    @AConstraint(
            mappings = Mappings.YARN_INTERMEDIARY,
            version = @Versions(min = MinecraftVersion.V14))
})
@Mixin(ResourceLocation.class)
@Implements(@Interface(iface = Identifier.class, prefix = "identifier$", remap = Remap.NONE))
public abstract class ResourceLocation_API {
    @Shadow
    public abstract String shadow$getNamespace();

    @Shadow
    public abstract String shadow$getPath();

    @Intrinsic
    public @NonNull String identifier$namespace() {
        return this.shadow$getNamespace();
    }

    @Intrinsic
    public @NonNull String identifier$path() {
        return this.shadow$getPath();
    }
}
