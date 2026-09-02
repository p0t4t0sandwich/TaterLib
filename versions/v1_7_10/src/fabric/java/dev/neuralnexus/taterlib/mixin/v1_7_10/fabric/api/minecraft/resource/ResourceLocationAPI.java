/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_7_10.fabric.api.minecraft.resource;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.anno.AConstraint;
import dev.neuralnexus.taterapi.meta.anno.Versions;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.resources.Identifier;

import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Interface.Remap;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@AConstraint(
        mappings = Mappings.LEGACY_INTERMEDIARY,
        version = @Versions(min = MinecraftVersion.V7_2, max = MinecraftVersion.V12_2))
@Mixin(net.minecraft.client.resource.Identifier.class)
@Implements(@Interface(iface = Identifier.class, prefix = "identifier$", remap = Remap.NONE))
public abstract class ResourceLocationAPI {
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
