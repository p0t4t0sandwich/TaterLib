/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.config.versions;

import dev.neuralnexus.taterapi.config.ToggleableSetting;
import dev.neuralnexus.taterlib.config.TaterLibConfig;
import dev.neuralnexus.taterapi.config.MixinConfig;
import dev.neuralnexus.taterlib.config.sections.ServerConfig;

import java.util.List;

/** A class for TaterLib configuration. */
public class TaterLibConfig_V1 implements TaterLibConfig {
    public final int version;
    public final List<ToggleableSetting> modules;
    public final List<ToggleableSetting> hooks;
    public final MixinConfig mixin;
    private final ServerConfig server;

    public TaterLibConfig_V1(
            int version,
            ServerConfig server,
            List<ToggleableSetting> modules,
            List<ToggleableSetting> hooks,
            MixinConfig mixin) {
        this.version = version;
        this.server = server;
        this.modules = modules;
        this.hooks = hooks;
        this.mixin = mixin;
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
    public List<ToggleableSetting> modules() {
        return modules;
    }

    @Override
    public List<ToggleableSetting> hooks() {
        return hooks;
    }

    @Override
    public MixinConfig mixin() {
        return mixin;
    }
}
