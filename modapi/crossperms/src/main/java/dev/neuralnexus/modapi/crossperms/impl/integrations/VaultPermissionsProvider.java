/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms.impl.integrations;

import dev.neuralnexus.modapi.crossperms.HasPermission;
import dev.neuralnexus.modapi.crossperms.PermissionsProvider;

import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

@SuppressWarnings({"Anonymous2MethodRef", "Convert2Lambda"})
public class VaultPermissionsProvider implements PermissionsProvider {
    private final Permission vault;

    @SuppressWarnings("DataFlowIssue")
    public VaultPermissionsProvider() {
        vault =
                Bukkit.getServer()
                        .getServicesManager()
                        .getRegistration(Permission.class)
                        .getProvider();
    }

    @Override
    public @NotNull Map<Class<?>, List<HasPermission<?, ?>>> getProviders() {
        return Map.of(
                CommandSender.class,
                List.of(
                        new HasPermission<String, CommandSender>() {
                            @Override
                            public boolean hasPermission(CommandSender subject, String permission) {
                                return vault.has(subject, permission);
                            }
                        }));
    }
}
