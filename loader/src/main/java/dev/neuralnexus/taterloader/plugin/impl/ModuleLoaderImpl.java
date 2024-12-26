/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterloader.plugin.impl;

import dev.neuralnexus.taterloader.plugin.ModuleLoader;
import dev.neuralnexus.taterloader.plugin.PluginModule;

import java.util.ArrayList;
import java.util.List;

public class ModuleLoaderImpl implements ModuleLoader {
    private static final List<PluginModule> modules = new ArrayList<>();

    @Override
    public List<PluginModule> modules() {
        return modules;
    }
}
