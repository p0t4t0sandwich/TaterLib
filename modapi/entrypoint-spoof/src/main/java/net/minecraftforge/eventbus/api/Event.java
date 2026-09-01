/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package net.minecraftforge.eventbus.api;

/** Fake Forge event. */
public class Event {
    public boolean isCanceled() {
        return false;
    }

    public void setCanceled(boolean cancel) {}
}
