/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package net.fabricmc.loader.api;

/** Fake Fabric interface. */
public interface Version extends Comparable<Version> {
    String getFriendlyString();
}
