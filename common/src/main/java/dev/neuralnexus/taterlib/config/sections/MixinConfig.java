/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.config.sections;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

import java.util.Set;

/** A class for parsing mixin configurations. */
@ConfigSerializable
public class MixinConfig {
    @Setting private boolean verbose;
    @Setting private Set<String> disabled;

    public boolean verbose() {
        return verbose;
    }

    public Set<String> disabled() {
        return disabled;
    }
}
