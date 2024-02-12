package dev.neuralnexus.taterlib.forge.hooks.permissions;

import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.forge.command.ForgeCommandSender;
import dev.neuralnexus.taterlib.forge.player.ForgePlayer;
import dev.neuralnexus.taterlib.hooks.permissions.PermissionsHook;
import dev.neuralnexus.taterlib.player.Player;

import net.minecraft.commands.CommandSourceStack;
import net.minecraftforge.server.permission.PermissionAPI;

/** A hook for Forge permissions */
public class ForgePermissionsHook implements PermissionsHook {
    /** {@inheritDoc} */
    @Override
    public String name() {
        return "forgepermissions";
    }

    /**
     * Get if a sender has a permission
     *
     * @param commandSender The sender to check
     * @param permission The permission to check
     * @return If the sender has the permission
     */
    @Override
    public boolean hasPermission(CommandSender commandSender, String permission) {
        if (commandSender.hasPermission(4)) {
            return true;
        }

        net.minecraft.world.entity.player.Player player;
        if (commandSender instanceof Player) {
            player = ((ForgePlayer) commandSender).player();
        } else {
            CommandSourceStack source = ((ForgeCommandSender) commandSender).sender();
            if (source.getEntity() == null) {
                return false;
            }
            player = (net.minecraft.world.entity.player.Player) source.getEntity();
        }
        return PermissionAPI.getPermissionHandler()
                .hasPermission(player.getGameProfile(), permission, null);
    }
}
