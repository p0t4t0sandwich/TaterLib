/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata;

import java.util.Arrays;

/** Enum for platform runtime mappings */
public enum Mappings {
    UNKNOWN("unknown"),
    NONE("none"),
    OFFICIAL("official"),
    MOJMAP("mojmap"),
    SPIGOT("spigot"),
    SEARGE("searge"),
    INTERMEDIARY("intermediary"),
    LEGACYINTERMEDIARY("legacy intermediary"),
    BABRICINTERMEDIARY("babric intermediary"),
    CALAMUS("calamus"),
    HASHED("hashed");

    private final String name;

    Mappings(String name) {
        this.name = name;
    }

    public boolean is(Mappings mappings) {
        return this == mappings;
    }

    public boolean is(String name) {
        return name.contains(this.name);
    }

    public static Mappings from(String name) {
        return Arrays.stream(values())
                .filter(mappings -> mappings.is(name))
                .findFirst()
                .orElse(UNKNOWN);
    }

    @Override
    public String toString() {
        return name;
    }
}
