package dev.neuralnexus.taterlib.v1_19.fabric.hooks.permissions;

import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.entity.Permissible;
import dev.neuralnexus.taterlib.hooks.permissions.PermissionsHook;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.v1_19.vanilla.command.VanillaCommandSender;
import dev.neuralnexus.taterlib.v1_19.vanilla.player.VanillaPlayer;

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
            return Permissions.check(((VanillaPlayer) permissible).player(), permission, 4);
        } else if (permissible instanceof CommandSender) {
            return Permissions.check(((VanillaCommandSender) permissible).sender(), permission, 4);
        }
        return false;
    }
}
