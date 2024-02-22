package dev.neuralnexus.taterlib;

import dev.neuralnexus.taterlib.plugin.ModuleLoader;
import dev.neuralnexus.taterlib.plugin.PluginModule;

import java.util.ArrayList;
import java.util.List;

/** TaterLib implementation of the ModuleLoader. */
public class TaterLibModuleLoader implements ModuleLoader {
    private static final List<PluginModule> modules = new ArrayList<>();

    /** {@inheritDoc} */
    @Override
    public List<PluginModule> modules() {
        return modules;
    }
}
