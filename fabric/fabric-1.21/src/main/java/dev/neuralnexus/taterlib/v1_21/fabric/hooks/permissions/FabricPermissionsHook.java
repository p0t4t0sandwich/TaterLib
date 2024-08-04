/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_21.fabric.hooks.permissions;

import dev.neuralnexus.taterapi.command.CommandSender;
import dev.neuralnexus.taterapi.entity.Permissible;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.hooks.permissions.PermissionsHook;

import me.lucko.fabric.api.permissions.v0.Permissions;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.world.entity.Entity;

/** A hook for Fabric permissions */
public class FabricPermissionsHook implements PermissionsHook {
    @Override
    public String name() {
        return "fabricpermissions";
    }

    @Override
    public boolean hasPermission(Permissible permissible, String permission) {
        if (permissible instanceof Player) {
            return Permissions.check((Entity) permissible, permission, 4);
        } else if (permissible instanceof CommandSender) {
            return Permissions.check((CommandSourceStack) permissible, permission, 4);
        }
        return false;
    }
}
