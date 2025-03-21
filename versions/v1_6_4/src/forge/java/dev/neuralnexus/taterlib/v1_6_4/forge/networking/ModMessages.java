/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_6_4.forge.networking;

import net.minecraft.entity.player.EntityPlayerMP;

import java.util.HashSet;
import java.util.Set;

public class ModMessages {
    private static final String PROTOCOL_VERSION = "1";
    private static final Set<String> channelQueue = new HashSet<>();
    //    private static final HashMap<String, SimpleNetworkWrapper> channels = new HashMap<>();
    private static int packetId = 0;

    private static int id() {
        return packetId++;
    }

    public static void addChannels(Set<String> channel) {
        channelQueue.addAll(channel);
    }

    public static void register() {
        //        SimpleNetworkWrapper wrapper =
        // NetworkRegistry.INSTANCE.newSimpleChannel("MYCHANNEL");

        for (String c : channelQueue) {
            String[] channelParts = c.split(":");
            //            channels.put(c, NetworkRegistry.INSTANCE.newSimpleChannel(c)
            //                    .networkProtocolVersion(() -> PROTOCOL_VERSION)
            //                    .clientAcceptedVersions(s -> true)
            //                    .serverAcceptedVersions(s -> true)
            //                    .simpleChannel());
            //            channels.get(c).registerMessage(id(),
            //                    ForgeMessagePacket.class,
            //                    ForgeMessagePacket::encode,
            //                    ForgeMessagePacket::decode,
            //                    Side.SERVER
            //            );
        }
    }

    public static void clearQueue() {
        channelQueue.clear();
    }

    public static <MSG> void sendPluginMessage(MSG message, String channel, EntityPlayerMP player) {
        //        channels.get(channel).sendTo(message, player.connection.netManager,
        // NetworkDirection.PLAY_TO_CLIENT);
    }
}
