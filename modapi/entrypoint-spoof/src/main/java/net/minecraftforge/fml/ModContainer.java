package net.minecraftforge.fml;

import net.minecraftforge.forgespi.language.IModInfo;

public class ModContainer {
    private IModInfo modInfo;
    private String modId;

    public IModInfo getModInfo() {
        return this.modInfo;
    }

    public final String getModId() {
        return this.modId;
    }
}
