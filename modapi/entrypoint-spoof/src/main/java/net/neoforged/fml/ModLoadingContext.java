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
