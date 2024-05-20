package dev.neuralnexus.taterlib.v1_11.sponge.hooks.permissions;

import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.entity.Permissible;
import dev.neuralnexus.taterlib.hooks.permissions.PermissionsHook;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.v1_11.sponge.command.SpongeCommandSender;
import dev.neuralnexus.taterlib.v1_11.sponge.player.SpongePlayer;

/** A hook for Sponge permissions */
public class SpongePermissionsHook implements PermissionsHook {
    /** {@inheritDoc} */
    @Override
    public String name() {
        return "spongepermissions";
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasPermission(Permissible permissible, String permission) {
        if (permissible instanceof Player) {
            return (((SpongePlayer) permissible).player()).hasPermission(permission);
        } else if (permissible instanceof CommandSender) {
            return (((SpongeCommandSender) permissible).sender().hasPermission(permission));
        }
        return false;
    }
}
