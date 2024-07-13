/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.velocity.v3_3_0.hooks.permissions;

import dev.neuralnexus.taterapi.command.CommandSender;
import dev.neuralnexus.taterapi.entity.Permissible;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.hooks.permissions.PermissionsHook;
import dev.neuralnexus.taterlib.velocity.v3_3_0.command.VelocityCommandSender;
import dev.neuralnexus.taterlib.velocity.v3_3_0.entity.player.VelocityPlayer;

/** A hook for Velocity permissions */
public class VelocityPermissionsHook implements PermissionsHook {
    @Override
    public String name() {
        return "spongepermissions";
    }

    @Override
    public boolean hasPermission(Permissible permissible, String permission) {
        if (permissible instanceof Player) {
            return ((VelocityPlayer) permissible).player().hasPermission(permission);
        } else if (permissible instanceof CommandSender) {
            return ((VelocityCommandSender) permissible).sender().hasPermission(permission);
        }
        return false;
    }
}
