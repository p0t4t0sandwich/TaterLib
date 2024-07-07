/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_8.sponge.hooks.permissions;

import dev.neuralnexus.taterapi.command.CommandSender;
import dev.neuralnexus.taterapi.entity.Permissible;
import dev.neuralnexus.taterapi.hooks.permissions.PermissionsHook;
import dev.neuralnexus.taterapi.player.Player;
import dev.neuralnexus.taterlib.v1_8.sponge.command.SpongeCommandSender;
import dev.neuralnexus.taterlib.v1_8.sponge.player.SpongePlayer;

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
