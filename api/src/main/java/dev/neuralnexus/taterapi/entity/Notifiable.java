/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLibLite/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.entity;

import java.util.UUID;

/** Entities that are able to receive messages */
public interface Notifiable {
    UUID NIL_UUID = new UUID(0, 0);

    /**
     * Send a message to the entity
     *
     * @param message The message to send
     */
    void sendMessage(String message);
}
