package dev.neuralnexus.taterlib.fabric.hooks.permissions;

import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.entity.Permissible;
import dev.neuralnexus.taterlib.fabric.command.FabricCommandSender;
import dev.neuralnexus.taterlib.fabric.player.FabricPlayer;
import dev.neuralnexus.taterlib.hooks.permissions.PermissionsHook;
import dev.neuralnexus.taterlib.player.Player;

import me.lucko.fabric.api.permissions.v0.Permissions;

/** A hook for Fabric permissions */
public class FabricPermissionsHook implements PermissionsHook {
    /** {@inheritDoc} */
    @Override
    public String name() {
        return "fabricpermissions";
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasPermission(Permissible permissible, String permission) {
        if (permissible instanceof Player) {
            return Permissions.check(((FabricPlayer) permissible).player(), permission, 4);
        } else if (permissible instanceof CommandSender) {
            return Permissions.check(((FabricCommandSender) permissible).sender(), permission, 4);
        }
        return false;
    }
}
