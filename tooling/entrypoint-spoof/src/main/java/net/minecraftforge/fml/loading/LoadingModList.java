package net.minecraftforge.fml.loading;

import net.minecraftforge.fml.loading.moddiscovery.ModFileInfo;
import net.minecraftforge.fml.loading.moddiscovery.ModInfo;

import java.util.Collections;
import java.util.List;

/**
 * Master list of all mods <em>in the loading context. This class cannot refer outside the
 * loading package</em>
 */
public class LoadingModList {
    private static LoadingModList INSTANCE;

    public static LoadingModList get() {
        return INSTANCE;
    }

    public ModFileInfo getModFileById(String modid) {
        return new ModFileInfo();
    }

    public List<ModInfo> getMods() {
        return Collections.emptyList();
    }
}
