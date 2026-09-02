/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package net.minecraftforge.api.distmarker;

/** Forge's "Side" enum */
public enum Dist {
    CLIENT,
    DEDICATED_SERVER;

    private Dist() {}

    public boolean isDedicatedServer() {
        return !this.isClient();
    }

    public boolean isClient() {
        return this == CLIENT;
    }
}
