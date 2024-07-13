/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_8_9.forge.hooks.permissions;

import dev.neuralnexus.taterapi.command.CommandSender;
import dev.neuralnexus.taterapi.entity.Permissible;
import dev.neuralnexus.taterapi.hooks.permissions.PermissionsHook;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterlib.v1_8_9.forge.command.ForgeSender;
import dev.neuralnexus.taterlib.v1_8_9.forge.player.ForgePlayer;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

/** A hook for Forge permissions */
public class ForgePermissionsHook implements PermissionsHook {
    @Override
    public String name() {
        return "forgepermissions";
    }

    @Override
    public boolean hasPermission(Permissible sender, String permission) {
        if (sender.hasPermission(4)) {
            return true;
        }

        EntityPlayer player;
        if (sender instanceof Player) {
            player = ((ForgePlayer) sender).getPlayer();
        } else if (sender instanceof CommandSender) {
            ICommandSender source = ((ForgeSender) sender).sender();
            if (source.getCommandSenderEntity() == null) {
                return false;
            }
            player = (EntityPlayer) source.getCommandSenderEntity();
        } else {
            player = null;
        }
        if (player != null) {
            //            return PermissionAPI.getPermissionHandler()
            //                    .hasPermission(player.getGameProfile(), permission, null);
        }
        return false;
    }
}
