/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.perms.impl.providers;

import dev.neuralnexus.taterapi.perms.HasPermission;
import dev.neuralnexus.taterapi.perms.PermissionsProvider;
import dev.neuralnexus.taterapi.perms.PermsAPI;
import dev.neuralnexus.taterapi.perms.mc.WCommandSender;
import dev.neuralnexus.taterapi.perms.mc.WEntity;
import dev.neuralnexus.taterapi.perms.mc.WPlayerList;
import dev.neuralnexus.taterapi.perms.mc.WServerPlayer;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

/** Vanilla permissions provider */
@SuppressWarnings({"Anonymous2MethodRef", "Convert2Lambda"})
public class VanillaPermissionsProvider implements PermissionsProvider {
    @Override
    public @NotNull Map<Class<?>, List<HasPermission<?, ?>>> getProviders() {
        return Map.of(
                Object.class,
                List.of(
                        new HasPermission<Integer, Object>() {
                            @Override
                            public boolean hasPermission(Object subject, Integer permission) {
                                return playerHasPermission(subject, permission);
                            }
                        }));
    }

    private boolean playerHasPermission(@NotNull Object subject, int permissionLevel) {
        // TODO: Query Bukkit vanilla objects. It's gonna suck to get all those obsfed mappings
        if (WPlayerList.is13_up) {
            if (WCommandSender.instanceOf(subject)) {
                return WCommandSender.wrap(subject).hasPermission(permissionLevel);
            } else if (WEntity.instanceOf(subject)) {
                return WServerPlayer.wrap(subject).hasPermission(permissionLevel);
            }
        }
        return PermsAPI.instance()
                .getGameProfile(subject)
                .filter(profile -> WPlayerList.hasPermissionLevel(profile, permissionLevel))
                .isPresent();
    }
}
