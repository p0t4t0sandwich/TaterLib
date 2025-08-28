/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.plugin;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.MinecraftVersion;
import dev.neuralnexus.taterapi.meta.Platform;
import dev.neuralnexus.taterapi.meta.Platforms;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public final class PluginLoader<T extends ResolvableEntrypoint> {
    private static final Mappings mappings = MetaAPI.instance().mappings();
    private static final Platform platform = MetaAPI.instance().platform();
    private static final MinecraftVersion minecraftVersion = MetaAPI.instance().version();

    private final ServiceLoader<T> loader;
    private final List<T> plugins = new ArrayList<>();

    public PluginLoader(Class<T> pluginClass) {
        loader = ServiceLoader.load(pluginClass);
    }

    public void load() {
        for (T plugin : loader) {
            if (plugin.mappings() != Mappings.NONE && !mappings.is(plugin.mappings())) {
                continue;
            }
            boolean platformMatch = false;
            for (var p : plugin.platforms()) {
                if (p == Platforms.UNKNOWN || p == platform) {
                    platformMatch = true;
                    break;
                }
            }
            if (!platformMatch) {
                continue;
            }
            if (!minecraftVersion.isInRange(plugin.min(), plugin.max())) {
                continue;
            }
            plugins.add(plugin);
        }
    }

    public void init() {
        for (T plugin : plugins) {
            plugin.onInit();
        }
    }
}
