/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms.mixin.l_searge;

import dev.neuralnexus.modapi.crossperms.PermsAPI;
import dev.neuralnexus.taterapi.entity.Identifiable;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.world.entity.Entity;

import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;

@ReqMappings(Mappings.LEGACY_SEARGE)
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
