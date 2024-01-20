package dev.neuralnexus.taterlib.forge.hooks.permissions;

import dev.neuralnexus.taterlib.command.Sender;
import dev.neuralnexus.taterlib.forge.command.ForgeSender;
import dev.neuralnexus.taterlib.forge.player.ForgePlayer;
import dev.neuralnexus.taterlib.hooks.permissions.PermissionsHook;
import dev.neuralnexus.taterlib.player.Player;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

/** A hook for Forge permissions */
public class ForgePermissionsHook implements PermissionsHook {
    /** {@inheritDoc} */
    @Override
    public String getName() {
        return "forgepermissions";
    }

    /**
     * Get if a sender has a permission
     *
     * @param sender The sender to check
     * @param permission The permission to check
     * @return If the sender has the permission
     */
    @Override
    public boolean hasPermission(Sender sender, String permission) {
        if (sender.hasPermission(4)) {
            return true;
        }

        EntityPlayer player;
        if (sender instanceof Player) {
            player = ((ForgePlayer) sender).getPlayer();
        } else {
            ICommandSender source = ((ForgeSender) sender).getSender();
            //            if (source.getCommandSenderEntity() == null) {
            //                return false;
            //            }
            //            player = (EntityPlayer) source.getCommandSenderEntity();
        }

        //        return PermissionAPI.getPermissionHandler()
        //                .hasPermission(player.getGameProfile(), permission, null);
        return false;
    }
}
