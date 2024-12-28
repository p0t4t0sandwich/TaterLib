/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.config;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Comment;

import java.util.HashSet;
import java.util.Set;

/** A class for parsing mixin configurations. */
@ConfigSerializable
public class MixinConfig {
    @Comment("Enable to print verbose mixin information during startup")
    private boolean verbose;
    {
        verbose = false;
    }

    @Comment("A list of mixins to disable")
    private Set<String> disabled = new HashSet<>();
    {
        disabled.add("dev.neuralnexus.taterlib.mixin.MixinExample");
        disabled.add("SomeMixin");
    }

    public boolean verbose() {
        return verbose;
    }

    public Set<String> disabled() {
        return disabled;
    }
}
