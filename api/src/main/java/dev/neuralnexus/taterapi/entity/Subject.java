/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.entity;

import dev.neuralnexus.taterapi.data.DataHolder;

/**
 * The base abstraction for a Minecraft player. Effectively a placeholder so generics don't scream.
 */
public interface Subject extends DataHolder, Identifiable, Nameable, Notifiable, ServerAware {}
