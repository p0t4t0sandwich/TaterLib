/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterloader.depdownloader;

import org.jetbrains.annotations.ApiStatus;

import java.net.MalformedURLException;
import java.util.Map;

@ApiStatus.Experimental
public class DepClassLoader extends DepDownloader {
    private final URLClassLoaderAccess access;

    public DepClassLoader(ClassLoader classLoader, String[] repos, Map<String, String> deps) {
        super(repos, deps);
        access = URLClassLoaderAccess.create(classLoader);
    }

    public void addDepsToClasspath() {
        for (MavenDependency dep : deps) {
            try {
                access.addURL(dep.filePath().toUri().toURL());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }
}
