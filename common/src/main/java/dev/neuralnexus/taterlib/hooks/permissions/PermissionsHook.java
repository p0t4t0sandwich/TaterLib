package dev.neuralnexus.taterlib.hooks.permissions;

import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.hooks.Hook;

/** A generic hook for permissions plugins */
public interface PermissionsHook extends Hook {
    /**
     * Get if a sender has a permission
     *
     * @param commandSender The sender to check
     * @param permission The permission to check
     * @return If the sender has the permission
     */
    boolean hasPermission(CommandSender commandSender, String permission);
}
