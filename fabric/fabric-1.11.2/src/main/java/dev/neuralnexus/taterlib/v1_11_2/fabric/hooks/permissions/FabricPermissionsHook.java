/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_11_2.fabric.hooks.permissions;

import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.entity.Permissible;
import dev.neuralnexus.taterlib.hooks.permissions.PermissionsHook;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.v1_11_2.fabric.command.FabricCommandSender;
import dev.neuralnexus.taterlib.v1_11_2.fabric.player.FabricPlayer;

import net.legacyfabric.fabric.api.permission.v1.PermissionsApiHolder;
import net.minecraft.entity.player.ServerPlayerEntity;

/** A hook for Fabric permissions */
public class FabricPermissionsHook implements PermissionsHook {
    /** {@inheritDoc} */
    @Override
    public String name() {
        return "fabricpermissions";
    }

    /** {@inheritDoc} */
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
