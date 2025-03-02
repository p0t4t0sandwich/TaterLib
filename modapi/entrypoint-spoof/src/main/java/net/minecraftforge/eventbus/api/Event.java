/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package net.minecraftforge.eventbus.api;

/** Fake Forge event. */
public class Event {
    public boolean isCanceled() {
        return false;
    }

    public void setCanceled(boolean cancel) {}
}
