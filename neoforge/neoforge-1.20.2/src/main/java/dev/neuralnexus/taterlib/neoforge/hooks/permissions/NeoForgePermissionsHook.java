package dev.neuralnexus.taterlib.neoforge.hooks.permissions;

import dev.neuralnexus.taterlib.command.Sender;
import dev.neuralnexus.taterlib.hooks.permissions.PermissionsHook;
import dev.neuralnexus.taterlib.neoforge.command.NeoForgeSender;
import dev.neuralnexus.taterlib.neoforge.player.NeoForgePlayer;
import dev.neuralnexus.taterlib.player.Player;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.server.permission.PermissionAPI;
import net.neoforged.neoforge.server.permission.exceptions.UnregisteredPermissionException;
import net.neoforged.neoforge.server.permission.nodes.PermissionNode;
import net.neoforged.neoforge.server.permission.nodes.PermissionTypes;

/** A hook for NeoForge permissions */
public class NeoForgePermissionsHook implements PermissionsHook {
    /** {@inheritDoc} */
    @Override
    public String getName() {
        return "neoforgepermissions";
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
            p = ((NeoForgePlayer) sender).getPlayer();
        } else {
            CommandSourceStack source = ((NeoForgeSender) sender).getSender();
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
