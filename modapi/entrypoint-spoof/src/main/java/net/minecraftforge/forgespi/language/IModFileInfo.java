package net.minecraftforge.forgespi.language;

import net.minecraftforge.forgespi.locating.IModFile;

import java.util.List;
import java.util.Map;

public interface IModFileInfo
{
    List<IModInfo> getMods();

    //UnmodifiableConfig getConfig();

    String getModLoader();

    //VersionRange getModLoaderVersion();

    boolean showAsResourcePack();

    Map<String,Object> getFileProperties();

    IModFile getFile();
}
