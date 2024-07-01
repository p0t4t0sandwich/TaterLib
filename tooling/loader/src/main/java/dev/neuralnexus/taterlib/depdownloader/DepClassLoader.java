/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.depdownloader;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.loader.impl.LoaderImpl;

import java.net.URLClassLoader;
import java.util.Map;

public class DepClassLoader extends DepDownloader {
    private final URLClassLoaderAccess access;

    public DepClassLoader(ClassLoader classLoader, String[] repos, Map<String, String> deps) {
        super(repos, deps);
        access = URLClassLoaderAccess.create((URLClassLoader) classLoader);
    }

    public void addDepsToClasspath() {
        for (String dependency : deps.keySet()) {
            try {
                access.addURL(
                        getJarPath(
                                TaterAPIProvider.api()
                                        .get()
                                        .platformData()
                                        .configFolder()
                                        .resolve(LoaderImpl.PROJECT_ID),
                                dependency)
                                .toUri()
                                .toURL());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
