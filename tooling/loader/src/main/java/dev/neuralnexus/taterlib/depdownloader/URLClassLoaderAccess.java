/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.depdownloader;

import dev.neuralnexus.taterlib.api.Platform;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collection;

/**
 * Provides access to {@link URLClassLoader}#addURL. <br>
 * The above licencing does not apply, this code inherits the licence from the original source. <br>
 *
 * @author lucko (Luck) <luck@lucko.me>
 * @link <a
 *     href="https://github.com/LuckPerms/LuckPerms/blob/9e7a3d26e4992df88e9eb439fc2760c13ca2785e/common/src/main/java/me/lucko/luckperms/common/plugin/classpath/URLClassLoaderAccess.java#L39">source</a>
 */
public abstract class URLClassLoaderAccess {

    /**
     * Creates a {@link URLClassLoaderAccess} for the given class loader.
     *
     * @param classLoader the class loader
     * @return the access object
     */
    public static URLClassLoaderAccess create(ClassLoader classLoader) {
        if (Platform.isFabric()) {
            try {
                Class<?> fabricLauncherBase =
                        Class.forName("net.fabricmc.loader.launch.common.FabricLauncherBase");
                Method getLauncher = fabricLauncherBase.getDeclaredMethod("getLauncher");
                Object launcher = getLauncher.invoke(null);
                Method propose = launcher.getClass().getDeclaredMethod("propose", URL.class);
                return new URLClassLoaderAccess(classLoader) {
                    @Override
                    public void addURL(URL url) {
                        try {
                            propose.invoke(launcher, url);
                        } catch (ReflectiveOperationException e) {
                            e.printStackTrace();
                        }
                    }
                };
            } catch (ClassNotFoundException
                    | IllegalAccessException
                    | NoSuchMethodException
                    | InvocationTargetException ignored) {
            }
        }
        if (Platform.isForge()) {
            return new URLClassLoaderAccess(classLoader) {
                @Override
                public void addURL(URL url) {
                    new BasicClassLoader(classLoader).addJarToClasspath(url);
                }
            };
        }
        if (Reflection.isSupported()) {
            return new Reflection(((URLClassLoader) classLoader));
        }
        return new Unsafe((URLClassLoader) classLoader);
    }

    private final ClassLoader classLoader;

    protected URLClassLoaderAccess(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    /**
     * Adds the given URL to the class loader.
     *
     * @param url the URL to add
     */
    public abstract void addURL(URL url);

    /** Accesses using reflection, not supported on Java 9+. */
    private static class Reflection extends URLClassLoaderAccess {
        private static final Method ADD_URL_METHOD;

        static {
            Method addUrlMethod;
            try {
                addUrlMethod = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
                addUrlMethod.setAccessible(true);
            } catch (Exception e) {
                addUrlMethod = null;
            }
            ADD_URL_METHOD = addUrlMethod;
        }

        private static boolean isSupported() {
            return ADD_URL_METHOD != null;
        }

        Reflection(URLClassLoader classLoader) {
            super(classLoader);
        }

        @Override
        public void addURL(URL url) {
            try {
                ADD_URL_METHOD.invoke(super.classLoader, url);
            } catch (ReflectiveOperationException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Accesses using sun.misc.Unsafe, supported on Java 9+.
     *
     * @author Vaishnav Anil (https://github.com/slimjar/slimjar)
     */
    private static class Unsafe extends URLClassLoaderAccess {
        private static final sun.misc.Unsafe UNSAFE;

        static {
            sun.misc.Unsafe unsafe;
            try {
                Field unsafeField = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
                unsafeField.setAccessible(true);
                unsafe = (sun.misc.Unsafe) unsafeField.get(null);
            } catch (Throwable t) {
                unsafe = null;
            }
            UNSAFE = unsafe;
        }

        private final Collection<URL> unopenedURLs;
        private final Collection<URL> pathURLs;

        @SuppressWarnings("unchecked")
        Unsafe(URLClassLoader classLoader) {
            super(classLoader);

            Collection<URL> unopenedURLs;
            Collection<URL> pathURLs;
            try {
                Object ucp = fetchField(URLClassLoader.class, classLoader, "ucp");
                unopenedURLs = (Collection<URL>) fetchField(ucp.getClass(), ucp, "unopenedUrls");
                pathURLs = (Collection<URL>) fetchField(ucp.getClass(), ucp, "path");
            } catch (Throwable e) {
                unopenedURLs = null;
                pathURLs = null;
            }

            this.unopenedURLs = unopenedURLs;
            this.pathURLs = pathURLs;
        }

        private static Object fetchField(
                final Class<?> clazz, final Object object, final String name)
                throws NoSuchFieldException {
            Field field = clazz.getDeclaredField(name);
            long offset = UNSAFE.objectFieldOffset(field);
            return UNSAFE.getObject(object, offset);
        }

        @Override
        public void addURL(URL url) {
            synchronized (this.unopenedURLs) {
                this.unopenedURLs.add(url);
                this.pathURLs.add(url);
            }
        }
    }
}
