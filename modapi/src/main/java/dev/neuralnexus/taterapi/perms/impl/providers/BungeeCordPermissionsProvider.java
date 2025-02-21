/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.perms.impl.providers;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.perms.HasPermission;
import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.perms.PermissionsProvider;

import net.md_5.bungee.api.CommandSender;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

/** BungeeCord permissions provider */
@SuppressWarnings("Convert2Lambda")
public class BungeeCordPermissionsProvider implements PermissionsProvider {
    @Override
    public @NotNull Map<Class<?>, List<HasPermission<?, ?>>> getProviders() {
        return Map.of(
                CommandSender.class,
                List.of(
                        new HasPermission<String, CommandSender>() {
                            @Override
                            public boolean hasPermission(CommandSender subject, String permission) {
                                return subject instanceof CommandSender sender
                                        && sender.hasPermission(permission);
                            }
                        }));
    }
}
