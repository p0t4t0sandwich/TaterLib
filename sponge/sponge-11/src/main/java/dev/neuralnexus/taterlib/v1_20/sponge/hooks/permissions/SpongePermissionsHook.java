/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_20.sponge.hooks.permissions;

import dev.neuralnexus.taterapi.command.CommandSender;
import dev.neuralnexus.taterapi.entity.Permissible;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.hooks.permissions.PermissionsHook;

import org.spongepowered.api.command.CommandCause;
import org.spongepowered.api.entity.living.player.server.ServerPlayer;

/** A hook for Sponge permissions */
public class SpongePermissionsHook implements PermissionsHook {
    @Override
    public String name() {
        return "spongepermissions";
    }

    @Override
    public boolean hasPermission(Permissible permissible, String permission) {
        if (permissible instanceof Player) {
            return ((ServerPlayer) permissible).hasPermission(permission);
        } else if (permissible instanceof CommandSender) {
            return (((CommandCause) permissible).hasPermission(permission));
        }
        return false;
    }
}
