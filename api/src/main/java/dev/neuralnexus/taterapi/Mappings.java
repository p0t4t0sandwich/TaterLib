package dev.neuralnexus.taterapi;

/**
 * Enum for platform runtime mappings
 */
public enum Mappings {
    NONE("none"),
    OFFICIAL("official"),
    MOJMAP("mojmap"),
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

    @Override
    public String toString() {
        return name;
    }
}
