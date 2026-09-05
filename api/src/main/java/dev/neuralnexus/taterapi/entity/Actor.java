/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLibLite/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.entity;

/**
 * The base abstraction for an entity that has a name and ID. Effectively a placeholder so generics
 * don't scream. TODO: Expand this once Entity has Locatable and Teleportable
 */
public interface Actor extends Identifiable, Nameable {}
