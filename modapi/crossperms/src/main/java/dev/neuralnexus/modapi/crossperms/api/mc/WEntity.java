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
