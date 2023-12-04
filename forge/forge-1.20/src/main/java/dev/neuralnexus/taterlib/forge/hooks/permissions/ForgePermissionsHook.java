package dev.neuralnexus.taterlib.forge.hooks.permissions;

import dev.neuralnexus.taterlib.command.Sender;
import dev.neuralnexus.taterlib.forge.command.ForgeSender;
import dev.neuralnexus.taterlib.forge.player.ForgePlayer;
import dev.neuralnexus.taterlib.hooks.permissions.PermissionsHook;
import dev.neuralnexus.taterlib.player.Player;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.server.permission.PermissionAPI;
import net.minecraftforge.server.permission.exceptions.UnregisteredPermissionException;
import net.minecraftforge.server.permission.nodes.PermissionNode;
import net.minecraftforge.server.permission.nodes.PermissionTypes;

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

        net.minecraft.world.entity.player.Player p;
        if (sender instanceof Player) {
            p = ((ForgePlayer) sender).getPlayer();
        } else {
            CommandSourceStack source = ((ForgeSender) sender).getSender();
            if (source.getPlayer() == null) {
                return false;
            }
            p = source.getPlayer();
        }

        try {
            // Assumes the first string node is the modid
            String modid = permission.split("\\.")[0];
            String node = permission.substring(modid.length() + 1);
            ResourceLocation resourceLocation = new ResourceLocation(modid, node);
            return PermissionAPI.getOfflinePermission(
                    p.getUUID(),
                    new PermissionNode<>(
                            resourceLocation,
                            PermissionTypes.BOOLEAN,
                            (player, playerUUID, context) -> true));
        } catch (UnregisteredPermissionException ignored) {
            return false;
        }
    }
}
