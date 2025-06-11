/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.meta.impl.platform;

import static dev.neuralnexus.taterapi.util.ReflectionUtil.checkForClass;

import dev.neuralnexus.taterapi.meta.Platform;

public final class PlatformImpl implements Platform {
    private final String name;
    private final String[] classNames;
    private boolean cached = false;
    private boolean detected = false;

    public PlatformImpl(String name, String... classNames) {
        this.name = name;
        this.classNames = classNames;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public boolean detect(boolean force) {
        if (this.cached && !force) {
            return this.detected;
        }
        this.detected = checkForClass(this.classNames);
        this.cached = true;
        return this.detected;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
