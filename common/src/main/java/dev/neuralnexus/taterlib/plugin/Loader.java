package dev.neuralnexus.taterlib.plugin;

public interface Loader {
    /** Get the platform's server/plugin/container. */
    Object plugin();

    /** Get the platform's server. */
    Object server();

    /** Get the platform's logger. */
    Object logger();

    /** Register a plugin. */
    void registerPlugin(Plugin plugin);

    /** Unregister a plugin. */
    void unregisterPlugin(String pluginId);

    /** Unregister a plugin. */
    default void unregisterPlugin(Plugin plugin) {
        unregisterPlugin(plugin.id());
    }

    /** Run Init on all plugins. */
    void onInit();

    /** Run Enable on all plugins. */
    void onEnable();

    /** Run Disable on all plugins. */
    void onDisable();
}
