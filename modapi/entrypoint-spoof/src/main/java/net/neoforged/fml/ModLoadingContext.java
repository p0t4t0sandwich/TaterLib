/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package net.neoforged.fml;

public class ModLoadingContext {
    private ModContainer activeContainer;
    private static ThreadLocal<ModLoadingContext> context;

    public static ModLoadingContext get() {
        return context.get();
    }

    public ModContainer getActiveContainer() {
        return this.activeContainer;
    }
}
