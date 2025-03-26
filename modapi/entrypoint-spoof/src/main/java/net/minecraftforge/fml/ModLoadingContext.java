package net.minecraftforge.fml;

import net.minecraftforge.eventbus.api.IEventBus;

public class ModLoadingContext {
    private static final ThreadLocal<ModLoadingContext> context = ThreadLocal.withInitial(ModLoadingContext::new);
    private Object languageExtension;
    private IEventBus eventBus;

    public static ModLoadingContext get() {
        return context.get();
    }

    public IEventBus getEventBus() {
        return this.eventBus;
    }

    public <T> T extension() {
        return (T)this.languageExtension;
    }
}
