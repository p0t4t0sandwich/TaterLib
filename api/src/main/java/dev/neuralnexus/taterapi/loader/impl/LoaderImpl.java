/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.loader.impl;

import dev.neuralnexus.taterapi.event.api.LoaderEvents;
import dev.neuralnexus.taterapi.event.loader.LoaderInitializeEvent;
import dev.neuralnexus.taterapi.loader.Loader;
import dev.neuralnexus.taterapi.loader.plugin.ModuleLoader;
import dev.neuralnexus.taterapi.loader.plugin.Plugin;
import dev.neuralnexus.taterapi.meta.MetaAPI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Loader entry point. */
public final class LoaderImpl implements Loader {
    public static final String PROJECT_NAME = "TaterLib";
    public static final String PROJECT_ID = "taterlib";
    public static final String PROJECT_VERSION = "2.0.0-SNAPSHOT";
    public static final String PROJECT_AUTHORS = "p0t4t0sandwich";
    public static final String PROJECT_DESCRIPTION =
            "A cross API code library that allows developers to write code that works across multiple modding platforms, and across a wide range of Minecraft versions, all with one JAR file. If TaterLib runs on it, so can your plugin/mod.";
    public static final String PROJECT_URL = "https://github.com/p0t4t0sandwich/TaterLib";
    private static Loader instance;
    private final Object plugin;
    private final List<Plugin> plugins = new ArrayList<>();
    private final Map<String, ModuleLoader> pluginModules = new HashMap<>();

    public LoaderImpl(Object plugin) {
        if (CheckForBad.checkForTLauncher()) {
            throw new RuntimeException("TaterLib does not support TLauncher");
        }
        if (!MetaAPI.instance().isModLoaded("handsoffmydata") && CheckForBad.checkForBrightSDK()) {
            throw new RuntimeException(
                    "TaterLib does not support environments containing BrightSDK, please install HandsOffMyData to ensure that your data is safe.");
        }

        instance = this;
        this.plugin = plugin;

        LoaderEvents.INIT.invoke(new LoaderInitializeEvent() {});
    }

    public static Loader getInstance() {
        return instance;
    }

    @Override
    public Object plugin() {
        return plugin;
    }

    @Override
    public List<Plugin> plugins() {
        return plugins;
    }

    @Override
    public Map<String, ModuleLoader> pluginModules() {
        return pluginModules;
    }
}
