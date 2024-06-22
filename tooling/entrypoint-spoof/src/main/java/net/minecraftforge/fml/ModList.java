package net.minecraftforge.fml;

import net.minecraftforge.fml.loading.moddiscovery.ModInfo;

import java.util.Collections;
import java.util.List;

/** Fake Forge modlist. */
public class ModList {
    private static final ModList INSTANCE = new ModList();

    public static ModList get() {
        return INSTANCE;
    }

    public List<ModInfo> getMods() {
        return Collections.emptyList();
    }
}
