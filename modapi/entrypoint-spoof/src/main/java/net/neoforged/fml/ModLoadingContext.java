/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
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
