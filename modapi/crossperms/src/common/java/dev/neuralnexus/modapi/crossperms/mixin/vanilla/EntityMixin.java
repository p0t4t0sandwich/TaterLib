/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms.mixin.vanilla;

import dev.neuralnexus.taterapi.entity.Identifiable;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.anno.AConstraint;

import net.minecraft.world.entity.Entity;

import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Interface.Remap;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.UUID;

@AConstraint(mappings = Mappings.MOJANG)
@Mixin(Entity.class)
@Implements(@Interface(iface = Identifiable.class, prefix = "identifiable$", remap = Remap.NONE))
public abstract class EntityMixin {
    // spotless:off
    @Shadow public abstract UUID shadow$getUUID();
    // spotless:on

    @Intrinsic
    public UUID identifiable$uuid() {
        return this.shadow$getUUID();
    }
}
