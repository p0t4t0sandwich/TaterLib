package org.bukkit.scheduler;

import org.bukkit.plugin.Plugin;

/** Fake Bukkit Scheduler class. */
public interface BukkitScheduler {
    int scheduleSyncDelayedTask(Plugin var1, Runnable var2);
}
