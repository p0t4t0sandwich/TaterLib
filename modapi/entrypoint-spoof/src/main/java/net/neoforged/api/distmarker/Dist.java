/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package net.neoforged.api.distmarker;

/** NeoForge's "Side" enum */
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
