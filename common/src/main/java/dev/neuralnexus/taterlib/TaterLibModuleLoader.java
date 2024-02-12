package dev.neuralnexus.taterlib;

import dev.neuralnexus.taterlib.plugin.ModuleLoader;
import dev.neuralnexus.taterlib.plugin.PluginModule;

import java.util.HashSet;
import java.util.Set;

/** TaterLib implementation of the ModuleLoader. */
public class TaterLibModuleLoader implements ModuleLoader {
    private static final Set<PluginModule> modules = new HashSet<>();

    /** {@inheritDoc} */
    @Override
    public Set<PluginModule> modules() {
        return modules;
    }
}
