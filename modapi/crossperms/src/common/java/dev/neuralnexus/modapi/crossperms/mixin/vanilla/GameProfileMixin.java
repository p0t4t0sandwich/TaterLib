/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms.mixin.vanilla;

import com.mojang.authlib.GameProfile;

import dev.neuralnexus.modapi.crossperms.PermsAPI;
import dev.neuralnexus.taterapi.entity.Identifiable;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;

import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Interface.Remap;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.UUID;

@ReqMCVersion(min = MinecraftVersion.V14)
@Mixin(GameProfile.class)
@Implements(@Interface(iface = Identifiable.class, prefix = "identifiable$", remap = Remap.NONE))
public abstract class GameProfileMixin {
    // spotless:off
    @Shadow public abstract UUID shadow$getId();
    // spotless:on

    @Intrinsic
    public UUID identifiable$uuid() {
        return this.shadow$getId();
    }

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
