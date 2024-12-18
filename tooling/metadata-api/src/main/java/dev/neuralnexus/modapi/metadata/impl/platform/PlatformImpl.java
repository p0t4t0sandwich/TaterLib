/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata.impl.platform;

import static dev.neuralnexus.modapi.metadata.impl.util.ReflectionUtil.checkForClass;

import dev.neuralnexus.modapi.metadata.Platform;

public class PlatformImpl implements Platform {
    private final String name;
    private final String classNames[];
    private boolean cached = false;
    private boolean detected = false;

    public PlatformImpl(String name, String... classNames) {
        this.name = name;
        this.classNames = classNames;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public boolean detect() {
        if (cached) {
            return detected;
        }
        detected = checkForClass(classNames);
        cached = true;
        return detected;
    }
}
