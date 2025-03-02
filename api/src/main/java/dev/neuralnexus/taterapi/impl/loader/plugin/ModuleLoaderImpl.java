/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.impl.loader.plugin;

import dev.neuralnexus.taterapi.loader.plugin.ModuleLoader;
import dev.neuralnexus.taterapi.loader.plugin.PluginModule;

import java.util.ArrayList;
import java.util.List;

public class ModuleLoaderImpl implements ModuleLoader {
    private static final List<PluginModule> modules = new ArrayList<>();

    @Override
    public List<PluginModule> modules() {
        return modules;
    }
}
