/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms.mc;

import dev.neuralnexus.modapi.crossperms.CrossPerms;
import dev.neuralnexus.taterapi.Wrapped;

/** Represents an Entity */
public class WEntity implements Wrapped<Object> {
    private final Object entity;

    private WEntity(Object entity) {
        this.entity = entity;
    }

    /**
     * Wrap an Entity object
     *
     * @param entity The Entity object to wrap
     * @return The wrapped Entity object
     */
    public static WEntity wrap(Object entity) {
        return new WEntity(entity);
    }

    @Override
    public Object unwrap() {
        return this.entity;
    }

    /**
     * Get the class of the Entity object
     *
     * @return The class of the Entity object
     */
    public static Class<?> getClazz() {
        return CrossPerms.instance().store().getClass("Entity");
    }

    /**
     * Check if an object is an instance of the Entity class
     *
     * @param obj The object to check
     * @return True if the object is an instance of the Entity class
     */
    public static boolean instanceOf(Object obj) {
        return getClazz().isInstance(obj);
    }
}
