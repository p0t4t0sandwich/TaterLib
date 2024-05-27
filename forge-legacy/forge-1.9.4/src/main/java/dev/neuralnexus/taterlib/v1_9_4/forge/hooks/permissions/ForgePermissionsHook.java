package dev.neuralnexus.taterlib.v1_9_4.forge.hooks.permissions;

import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.entity.Permissible;
import dev.neuralnexus.taterlib.hooks.permissions.PermissionsHook;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.v1_9_4.forge.command.ForgeSender;
import dev.neuralnexus.taterlib.v1_9_4.forge.player.ForgePlayer;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

/** A hook for Forge permissions */
public class ForgePermissionsHook implements PermissionsHook {
    /** {@inheritDoc} */
    @Override
    public String name() {
        return "forgepermissions";
    }

    /** {@inheritDoc} */
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
