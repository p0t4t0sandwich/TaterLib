/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.vanilla.bridge.item;

public interface ItemStackBridge {
    int accessor$size();

    void accessor$setSize(int size);

    default int bridge$size() {
        return this.accessor$size();
    }

    default void bridge$setSize(int size) {
        this.accessor$setSize(size);
    }
}
