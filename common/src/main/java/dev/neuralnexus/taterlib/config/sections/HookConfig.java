package dev.neuralnexus.taterlib.config.sections;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

/** A class for parsing hook configurations. */
@ConfigSerializable
public class HookConfig {
    @Setting private String name;
    @Setting private boolean enabled;

    /** Returns whether the hook is enabled. */
    public String name() {
        return name;
    }

    /** Returns whether the hook is enabled. */
    public boolean enabled() {
        return enabled;
    }
}
