package dev.neuralnexus.taterlib.config.versions;

import dev.neuralnexus.taterlib.config.Config;
import dev.neuralnexus.taterlib.config.sections.HookConfig;
import dev.neuralnexus.taterlib.config.sections.MixinConfig;
import dev.neuralnexus.taterlib.config.sections.ModuleConfig;
import dev.neuralnexus.taterlib.config.sections.ServerConfig;

import java.util.Set;

/** A class for TaterLib configuration. */
public class Config_V1 implements Config {
    public final int version;
    public final Set<ModuleConfig> modules;
    public final Set<HookConfig> hooks;
    public final Set<MixinConfig> mixins;
    private final ServerConfig server;

    public Config_V1(
            int version,
            ServerConfig server,
            Set<ModuleConfig> modules,
            Set<HookConfig> hooks,
            Set<MixinConfig> mixins) {
        this.version = version;
        this.server = server;
        this.modules = modules;
        this.hooks = hooks;
        this.mixins = mixins;
    }

    /** {@inheritDoc} */
    @Override
    public int version() {
        return version;
    }

    /** {@inheritDoc} */
    @Override
    public ServerConfig server() {
        return server;
    }

    /** {@inheritDoc} */
    @Override
    public Set<ModuleConfig> modules() {
        return modules;
    }

    /** {@inheritDoc} */
    @Override
    public Set<HookConfig> hooks() {
        return hooks;
    }

    /** {@inheritDoc} */
    @Override
    public Set<MixinConfig> mixins() {
        return mixins;
    }
}
