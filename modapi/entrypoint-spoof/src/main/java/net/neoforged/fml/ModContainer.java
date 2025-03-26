package net.neoforged.fml;

import net.neoforged.bus.api.IEventBus;
import org.jetbrains.annotations.Nullable;

public abstract class ModContainer {
    public abstract @Nullable IEventBus getEventBus();
}
