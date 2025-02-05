package dev.neuralnexus.modapi.crossperms.api.impl.integrations;

import dev.neuralnexus.modapi.crossperms.api.HasPermission;
import dev.neuralnexus.modapi.crossperms.api.PermissionsProvider;

import dev.neuralnexus.modapi.crossperms.api.PermsAPI;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

@SuppressWarnings({"Anonymous2MethodRef", "Convert2Lambda"})
public class LuckPermsPermissionsProvider implements PermissionsProvider {
    private final LuckPerms luckPerms;

    public LuckPermsPermissionsProvider() {
        this.luckPerms = LuckPermsProvider.get();
    }

    @Override
    public @NotNull Map<Class<?>, List<HasPermission<?, ?>>> getProviders() {
        return Map.of(
                Object.class,
                List.of(
                        new HasPermission<String, Object>() {
                            @Override
                            public boolean hasPermission(Object subject, String permission) {
                                return profileHasPermission(subject, permission);
                            }
                        }));
    }

    private boolean profileHasPermission(Object subject, String permission) {
        return PermsAPI.instance()
                .getGameProfile(subject)
                .map(profile -> this.luckPerms.getUserManager().getUser(profile.getId()))
                .map(user -> user.getCachedData().getPermissionData().checkPermission(permission).asBoolean())
                .orElse(false);
    }
}
