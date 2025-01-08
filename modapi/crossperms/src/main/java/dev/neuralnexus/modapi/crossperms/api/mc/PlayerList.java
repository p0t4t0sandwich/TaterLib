package dev.neuralnexus.modapi.crossperms.api.mc;

import com.mojang.authlib.GameProfile;
import dev.neuralnexus.modapi.crossperms.CrossPerms;
import dev.neuralnexus.modapi.metadata.MetaAPI;
import dev.neuralnexus.modapi.metadata.MinecraftVersions;

import java.util.List;

public class PlayerList {
    private static final boolean is7_10 = MetaAPI.instance().version().isInRange(MinecraftVersions.V7, MinecraftVersions.V7_10);
    private final Object playerList;

    public PlayerList(Object playerList) {
        this.playerList = playerList;
    }

    public List<?> getPlayers() {
        if (is7_10) {
            return (List<?>) CrossPerms.instance().store().getField("PlayerList", "players", playerList);
        } else {
            return (List<?>) CrossPerms.instance().store().invokeMethod("PlayerList", "getPlayers", playerList);
        }
    }

    public boolean isOp(GameProfile profile) {
        return CrossPerms.instance().store().invokeMethod("PlayerList", "isOp", playerList, profile);
    }
}
