package dev.neuralnexus.taterlib.velocity.v3_3_0.hooks.permissions;

import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.entity.Permissible;
import dev.neuralnexus.taterlib.hooks.permissions.PermissionsHook;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.velocity.v3_3_0.command.VelocityCommandSender;
import dev.neuralnexus.taterlib.velocity.v3_3_0.player.VelocityPlayer;

/** A hook for Velocity permissions */
public class VelocityPermissionsHook implements PermissionsHook {
    /** {@inheritDoc} */
    @Override
    public String name() {
        return "spongepermissions";
    }

    /** {@inheritDoc} */
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
