/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.crossperms.api.mc;

import dev.neuralnexus.modapi.crossperms.CrossPerms;

public class WEntity {
    private final Object entity;

    private WEntity(Object entity) {
        this.entity = entity;
    }

    public static WEntity wrap(Object entity) {
        return new WEntity(entity);
    }

    public static Class<?> getClazz() {
        return CrossPerms.instance().store().getClass("Entity");
    }

    public static boolean instanceOf(Object obj) {
        return getClazz().isInstance(obj);
    }
}
