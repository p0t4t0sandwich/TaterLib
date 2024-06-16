package dev.neuralnexus.conditionalmixins.api;

/** Stores information about a mod */
public class ModInfo {
    private final String id;
    private final String name;
    private final String version;

    public ModInfo(String id, String name, String version) {
        this.id = id;
        this.name = name;
        this.version = version;
    }

    /**
     * Get the mod id
     *
     * @return The mod id
     */
    public String id() {
        return id;
    }

    /**
     * Get the mod name
     *
     * @return The mod name
     */
    public String name() {
        return name;
    }

    /**
     * Get the mod version
     *
     * @return The mod version
     */
    public String version() {
        return version;
    }
}
