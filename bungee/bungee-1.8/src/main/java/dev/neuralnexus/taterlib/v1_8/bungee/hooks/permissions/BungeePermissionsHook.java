/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_8.bungee.hooks.permissions;

import dev.neuralnexus.taterapi.command.CommandSender;
import dev.neuralnexus.taterapi.entity.Permissible;
import dev.neuralnexus.taterapi.entity.player.ProxyPlayer;
import dev.neuralnexus.taterapi.hooks.permissions.PermissionsHook;
import dev.neuralnexus.taterlib.v1_8.bungee.command.BungeeCommandSender;
import dev.neuralnexus.taterlib.v1_8.bungee.entity.player.BungeePlayer;

/** A hook for Bungee permissions */
public class BungeePermissionsHook implements PermissionsHook {
    @Override
    public String name() {
        return "bungeepermissions";
    }

    @Override
    public boolean hasPermission(Permissible permissible, String permission) {
        if (permissible instanceof ProxyPlayer) {
            return ((BungeePlayer) permissible).player().hasPermission(permission);
        } else if (permissible instanceof CommandSender) {
            return ((BungeeCommandSender) permissible).sender().hasPermission(permission);
        }
        return false;
    }
}
