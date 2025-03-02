/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.config.versions;

import dev.neuralnexus.taterapi.config.MixinConfig;
import dev.neuralnexus.taterlib.config.TaterLibConfig;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Comment;
import org.spongepowered.configurate.objectmapping.meta.Required;

import java.util.HashMap;
import java.util.Map;

/** A class for TaterLib configuration. */
@ConfigSerializable
public class TaterLibConfig_V1 implements TaterLibConfig {
    @Comment("Config version, DO NOT CHANGE THIS")
    @Required
    private int version = 1;

    @Comment("Enable or disable TaterLib's modules")
    private Map<String, Boolean> modules = new HashMap<>();

    {
        modules.put("BungeeCord", false);
        modules.put("MCLogs", false);
    }

    @Comment("Enable or disable TaterLib's hooks")
    private Map<String, Boolean> hooks = new HashMap<>();

    {
        hooks.put("LuckPerms", true);
        hooks.put("Spark", true);
    }

    @Comment("Mixin configuration")
    private MixinConfig mixin = new MixinConfig();

    @Override
    public int version() {
        return version;
    }

    @Override
    public Map<String, Boolean> modules() {
        return modules;
    }

    @Override
    public Map<String, Boolean> hooks() {
        return hooks;
    }

    @Override
    public MixinConfig mixin() {
        return mixin;
    }
}
