package dev.neuralnexus.modapi.crossperms.api.impl;

import dev.neuralnexus.modapi.crossperms.api.PermissionsHook;
import dev.neuralnexus.modapi.crossperms.api.PermsAPI;

import java.util.Set;

/**
 * Permissions API implementation
 */
public class PermsAPIImpl implements PermsAPI {
    private static PermsAPIImpl INSTANCE = null;
    public static PermsAPIImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PermsAPIImpl();
        }
        return INSTANCE;
    }
    private PermsAPIImpl() {}

    private final Set<PermissionsHook> hooks = Set.of();

    @Override
    public Set<PermissionsHook> hooks() {
        return hooks;
    }
}
