/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package org.bukkit.scheduler;

import org.bukkit.plugin.Plugin;

/** Fake Bukkit Scheduler class. */
public interface BukkitScheduler {
    int scheduleSyncDelayedTask(Plugin var1, Runnable var2);
}
