/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms.mixin.searge;

import dev.neuralnexus.modapi.crossperms.PermsAPI;
import dev.neuralnexus.taterapi.entity.Identifiable;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.anno.AConstraint;
import dev.neuralnexus.taterapi.meta.anno.Versions;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.world.entity.Entity;

import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;

@AConstraint(mappings = Mappings.MOJANG, version = @Versions(min = MinecraftVersion.V17))
@Mixin(value = {CommandSourceStack.class, Entity.class})
@Implements(
        @Interface(
                iface = Identifiable.class,
                prefix = "identifiable$",
                remap = Interface.Remap.NONE))
public class Identifiable_API {
    @Intrinsic
    public boolean identifiable$hasPermission(String permission) {
        return PermsAPI.instance().hasPermission(this, permission);
    }

    @Intrinsic
    public boolean identifiable$hasPermission(int permissionLevel) {
        return PermsAPI.instance().hasPermission(this, permissionLevel);
    }

    @Intrinsic
    public boolean identifiable$hasPermission(String permission, int defaultPermissionLevel) {
        return PermsAPI.instance().hasPermission(this, permission, defaultPermissionLevel);
    }
}
