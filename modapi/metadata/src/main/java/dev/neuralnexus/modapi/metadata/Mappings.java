/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata;

/** Enum for platform runtime mappings */
public enum Mappings {
    UNKNOWN("unknown"),
    NONE("none"),
    OFFICIAL("official"),
    MOJMAP("mojmap"),
    SPIGOT("spigot"), // Spigot 1.18+
    LEGACY_SPIGOT("legacy spigot"), // Spigot 1.17-
    SEARGE("searge"), // Forge 1.17.1+
    LEGACY_SEARGE("legacy searge"), // Forge 1.16.5-
    MCP("mcp"),
    YARN("yarn"),
    YARN_INTERMEDIARY("yarn intermediary"), // Fabric 1.14+
    LEGACY_INTERMEDIARY("legacy intermediary"), // Fabric 1.13-
    BABRIC_INTERMEDIARY("babric intermediary"),
    CALAMUS("calamus"),
    HASHED("hashed");

    private final String name;

    Mappings(String name) {
        this.name = name;
    }

    public boolean is(Mappings mappings) {
        return this == mappings;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
