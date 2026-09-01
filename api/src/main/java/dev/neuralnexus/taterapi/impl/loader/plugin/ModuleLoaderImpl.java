/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
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
