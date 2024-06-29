/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.config.versions;

import dev.neuralnexus.taterlib.config.TaterLibConfig;
import dev.neuralnexus.taterlib.config.sections.HookConfig;
import dev.neuralnexus.taterlib.config.sections.MixinConfig;
import dev.neuralnexus.taterlib.config.sections.ModuleConfig;
import dev.neuralnexus.taterlib.config.sections.ServerConfig;

import java.util.List;

/** A class for TaterLib configuration. */
public class TaterLibConfig_V1 implements TaterLibConfig {
    public final int version;
    public final List<ModuleConfig> modules;
    public final List<HookConfig> hooks;
    public final List<MixinConfig> mixins;
    private final ServerConfig server;

    public TaterLibConfig_V1(
            int version,
            ServerConfig server,
            List<ModuleConfig> modules,
            List<HookConfig> hooks,
            List<MixinConfig> mixins) {
        this.version = version;
        this.server = server;
        this.modules = modules;
        this.hooks = hooks;
        this.mixins = mixins;
    }

    @Override
    public int version() {
        return version;
    }

    @Override
    public ServerConfig server() {
        return server;
    }

    @Override
    public List<ModuleConfig> modules() {
        return modules;
    }

    @Override
    public List<HookConfig> hooks() {
        return hooks;
    }

    @Override
    public List<MixinConfig> mixins() {
        return mixins;
    }
}
