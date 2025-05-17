package dev.neuralnexus.taterapi.entity.player;

import dev.neuralnexus.taterapi.entity.Identifiable;
import dev.neuralnexus.taterapi.entity.Nameable;
import dev.neuralnexus.taterapi.entity.Notifiable;
import dev.neuralnexus.taterapi.entity.ServerAware;

/**
 * The base abstraction for a Minecraft player.
 * Effectively a placeholder so generics don't scream.
 */
public interface Subject extends Identifiable, Nameable, Notifiable, ServerAware {}
