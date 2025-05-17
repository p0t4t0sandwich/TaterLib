/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.entity;

/**
 * The base abstraction for an entity that has a name and ID. Effectively a placeholder so generics
 * don't scream. TODO: Expand this once Entity has Locatable and Teleportable
 */
public interface Actor extends Identifiable, Nameable {}
