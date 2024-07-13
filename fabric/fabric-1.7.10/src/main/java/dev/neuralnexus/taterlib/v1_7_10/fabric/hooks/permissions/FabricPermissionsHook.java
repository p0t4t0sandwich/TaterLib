/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_7_10.fabric.hooks.permissions;

import dev.neuralnexus.taterapi.command.CommandSender;
import dev.neuralnexus.taterapi.entity.Permissible;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.hooks.permissions.PermissionsHook;
import dev.neuralnexus.taterlib.v1_7_10.fabric.command.FabricCommandSender;
import dev.neuralnexus.taterlib.v1_7_10.fabric.entity.player.FabricPlayer;

import net.legacyfabric.fabric.api.permission.v1.PermissionsApiHolder;
import net.minecraft.entity.player.ServerPlayerEntity;

/** A hook for Fabric permissions */
public class FabricPermissionsHook implements PermissionsHook {
    @Override
    public String name() {
        return "fabricpermissions";
    }

    @SuppressWarnings({"deprecation", "UnstableApiUsage"})
    @Override
    public boolean hasPermission(Permissible permissible, String permission) {
        if (permissible instanceof Player) {
            return PermissionsApiHolder.getPlayerPermissionsApi()
                    .hasPermission(
                            (ServerPlayerEntity) ((FabricPlayer) permissible).player(), permission);
        } else if (permissible instanceof CommandSender) {
            return ((FabricCommandSender) permissible).sender().hasPermission(permission);
        }
        return false;
    }
}
