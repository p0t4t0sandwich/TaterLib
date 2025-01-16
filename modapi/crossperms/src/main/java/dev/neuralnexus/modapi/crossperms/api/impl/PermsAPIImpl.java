/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms.api.impl;

import dev.neuralnexus.modapi.crossperms.api.PermissionsProvider;
import dev.neuralnexus.modapi.crossperms.api.PermsAPI;

import java.util.ArrayList;
import java.util.List;

/** Permissions API implementation */
public class PermsAPIImpl implements PermsAPI {
    private static PermsAPIImpl INSTANCE;

    public static PermsAPIImpl getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new PermsAPIImpl();
        }
        return INSTANCE;
    }

    private PermsAPIImpl() {}

    private final List<PermissionsProvider> providers = new ArrayList<>();

    @Override
    public List<PermissionsProvider> providers() {
        return this.providers;
    }

    @Override
    public void registerProvider(PermissionsProvider provider) {
        this.providers.addFirst(provider);
    }

    @Override
    public void unregisterProvider(String name) {
        this.providers.removeIf(provider -> provider.id().equalsIgnoreCase(name));
    }

    @Override
    public void unregisterProvider(PermissionsProvider provider) {
        this.providers.remove(provider);
    }
}
