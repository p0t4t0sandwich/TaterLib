package dev.neuralnexus.taterlib.api.info;

/** Stores information about a plugin */
public class PluginInfo {
    private final String name;
    private final String version;

    public PluginInfo(String name, String version) {
        this.name = name;
        this.version = version;
    }

    /**
     * Get the plugin name
     *
     * @return The plugin name
     */
    public String name() {
        return name;
    }

    /**
     * Get the plugin version
     *
     * @return The plugin version
     */
    public String version() {
        return version;
    }
}
