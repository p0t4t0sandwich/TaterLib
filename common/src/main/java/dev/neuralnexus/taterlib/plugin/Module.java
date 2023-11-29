package dev.neuralnexus.taterlib.plugin;

/** Module interface. */
public interface Module {
    /**
     * Get the name of the module.
     *
     * @return The name of the module
     */
    String getName();

    /** Start the module. */
    void start();

    /** Stop the module. */
    void stop();

    /** Reload the module. */
    void reload();
}
