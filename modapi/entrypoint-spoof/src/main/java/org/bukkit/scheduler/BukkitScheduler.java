/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package org.bukkit.scheduler;

import org.bukkit.plugin.Plugin;

/** Fake Bukkit Scheduler class. */
public interface BukkitScheduler {
    int scheduleSyncDelayedTask(Plugin var1, Runnable var2);
}
