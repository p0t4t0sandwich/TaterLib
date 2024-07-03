/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.depdownloader;

import java.net.URL;
import java.net.URLClassLoader;

public class BasicClassLoader extends URLClassLoader {
    static {
        ClassLoader.registerAsParallelCapable();
    }

    public BasicClassLoader(ClassLoader loaderClassLoader) {
        super(new URL[] {}, loaderClassLoader);
    }

    public void addJarToClasspath(URL url) {
        addURL(url);
    }
}
