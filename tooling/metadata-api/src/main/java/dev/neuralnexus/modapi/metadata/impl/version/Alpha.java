/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata.impl.version;

import static dev.neuralnexus.modapi.metadata.ProtocolType.PRE_NETTY;
import static dev.neuralnexus.modapi.metadata.ProtocolType.PRE_PRE_NETTY;

import dev.neuralnexus.modapi.metadata.MinecraftVersion;

// 5
@SuppressWarnings("unused")
public interface Alpha {
    MinecraftVersion A1_0_0 = MinecraftVersionImpl.of("a1.0.0");
    MinecraftVersion A1_0_1 = MinecraftVersionImpl.of("a1.0.1");
    MinecraftVersion A1_0_1_01 = MinecraftVersionImpl.of("a1.0.1_01");
    MinecraftVersion A1_0_2 = MinecraftVersionImpl.of("a1.0.2");
    MinecraftVersion A1_0_2_01 = MinecraftVersionImpl.of("a1.0.2_01");
    MinecraftVersion A1_0_2_02 = MinecraftVersionImpl.of("a1.0.2_02");
    MinecraftVersion A1_0_3 = MinecraftVersionImpl.of("a1.0.3");
    MinecraftVersion A1_0_4 = MinecraftVersionImpl.of("a1.0.4");
    MinecraftVersion A1_0_5 = MinecraftVersionImpl.of("a1.0.5", 10, PRE_PRE_NETTY);
    MinecraftVersion A1_0_5_01 = MinecraftVersionImpl.of("a1.0.5_01", 10, PRE_PRE_NETTY);
    MinecraftVersion A1_0_6 = MinecraftVersionImpl.of("a1.0.6", 10, PRE_PRE_NETTY);
    MinecraftVersion A1_0_6_01 = MinecraftVersionImpl.of("a1.0.6_01", 10, PRE_PRE_NETTY);
    MinecraftVersion A1_0_6_02 = MinecraftVersionImpl.of("a1.0.6_02", 10, PRE_PRE_NETTY);
    MinecraftVersion A1_0_6_03 = MinecraftVersionImpl.of("a1.0.6_03", 10, PRE_PRE_NETTY);
    MinecraftVersion A1_0_7 = MinecraftVersionImpl.of("a1.0.7", 10, PRE_PRE_NETTY);
    MinecraftVersion A1_0_8 = MinecraftVersionImpl.of("a1.0.8", 10, PRE_PRE_NETTY);
    MinecraftVersion A1_0_8_01 = MinecraftVersionImpl.of("a1.0.8_01", 10, PRE_PRE_NETTY);
    MinecraftVersion A1_0_9 = MinecraftVersionImpl.of("a1.0.9", 10, PRE_PRE_NETTY);
    MinecraftVersion A1_0_10 = MinecraftVersionImpl.of("a1.0.10", 10, PRE_PRE_NETTY);
    MinecraftVersion A1_0_11 = MinecraftVersionImpl.of("a1.0.11", 10, PRE_PRE_NETTY);
    MinecraftVersion A1_0_12 = MinecraftVersionImpl.of("a1.0.12", 11, PRE_PRE_NETTY);
    MinecraftVersion A1_0_13 = MinecraftVersionImpl.of("a1.0.13", 12, PRE_PRE_NETTY);
    MinecraftVersion A1_0_13_01 = MinecraftVersionImpl.of("a1.0.13_01", 12, PRE_PRE_NETTY);
    MinecraftVersion A1_0_14 = MinecraftVersionImpl.of("a1.0.14", 12, PRE_PRE_NETTY);
    MinecraftVersion A1_0_15 = MinecraftVersionImpl.of("a1.0.15", 13, PRE_PRE_NETTY);
    MinecraftVersion A_SERVER_0_1_0 =
            MinecraftVersionImpl.of("Alpha server 0.1.0", 13, PRE_PRE_NETTY); // Server for a1.0.15
    MinecraftVersion A1_0_16 = MinecraftVersionImpl.of("a1.0.16", 14, PRE_PRE_NETTY);
    MinecraftVersion A_SERVER_0_1_1 =
            MinecraftVersionImpl.of("Alpha server 0.1.1", 14, PRE_PRE_NETTY); // Server for a1.0.16
    MinecraftVersion A1_0_16_01 = MinecraftVersionImpl.of("a1.0.16_01", 14, PRE_PRE_NETTY);
    MinecraftVersion A1_0_16_02 = MinecraftVersionImpl.of("a1.0.16_02", 14, PRE_PRE_NETTY);
    MinecraftVersion A_SERVER_0_1_2 =
            MinecraftVersionImpl.of(
                    "Alpha server 0.1.2", 14, PRE_PRE_NETTY); // Server for a1.0.16_02
    MinecraftVersion A_SERVER_0_1_2_01 =
            MinecraftVersionImpl.of(
                    "Alpha server 0.1.2_01", 14, PRE_PRE_NETTY); // Server for a1.0.16_02
    MinecraftVersion A_SERVER_0_1_3 =
            MinecraftVersionImpl.of(
                    "Alpha server 0.1.3", 14, PRE_PRE_NETTY); // Server for a1.0.16_02
    MinecraftVersion A1_0_17 = MinecraftVersionImpl.of("a1.0.17", 1, PRE_NETTY);
    MinecraftVersion A1_0_17_01 = MinecraftVersionImpl.of("a1.0.17_01", 1, PRE_NETTY);
    MinecraftVersion A_SERVER_0_1_4 =
            MinecraftVersionImpl.of("Alpha server 0.1.4", 1, PRE_NETTY); // Server for a1.0.17
    MinecraftVersion A1_0_17_02 = MinecraftVersionImpl.of("a1.0.17_02", 1, PRE_NETTY);
    MinecraftVersion A1_0_17_03 = MinecraftVersionImpl.of("a1.0.17_03", 1, PRE_NETTY);
    MinecraftVersion A1_0_17_04 = MinecraftVersionImpl.of("a1.0.17_04", 1, PRE_NETTY);
    MinecraftVersion A1_1_0 = MinecraftVersionImpl.of("a1.1.0", 2, PRE_NETTY);
    MinecraftVersion A_SERVER_0_2_0 =
            MinecraftVersionImpl.of("Alpha server 0.2.0", 2, PRE_NETTY); // Server for a1.1.0
    MinecraftVersion A_SERVER_0_2_0_01 =
            MinecraftVersionImpl.of("Alpha server 0.2.0_01", 2, PRE_NETTY); // Server for a1.1.0
    MinecraftVersion A1_1_1 = MinecraftVersionImpl.of("a1.1.1", 2, PRE_NETTY);
    MinecraftVersion A_SERVER_0_2_1 =
            MinecraftVersionImpl.of("Alpha server 0.2.1", 2, PRE_NETTY); // Server for a1.1.1
    MinecraftVersion A1_1_2 = MinecraftVersionImpl.of("a1.1.2", 2, PRE_NETTY);
    MinecraftVersion A1_1_2_01 = MinecraftVersionImpl.of("a1.1.2_01", 2, PRE_NETTY);
    MinecraftVersion A1_2_0 = MinecraftVersionImpl.of("a1.2.0", 3, PRE_NETTY);
    MinecraftVersion A_SERVER_0_2_2 =
            MinecraftVersionImpl.of("Alpha server 0.2.2", 3, PRE_NETTY); // Server for a1.2.0
    MinecraftVersion A_SERVER_0_2_2_01 =
            MinecraftVersionImpl.of("Alpha server 0.2.2_01", 3, PRE_NETTY); // Server for a1.2.0
    MinecraftVersion A1_2_0_01 = MinecraftVersionImpl.of("a1.2.0_01", 3, PRE_NETTY);
    MinecraftVersion A1_2_0_02 = MinecraftVersionImpl.of("a1.2.0_02", 3, PRE_NETTY);
    MinecraftVersion A1_2_1 = MinecraftVersionImpl.of("a1.2.1", 3, PRE_NETTY);
    MinecraftVersion A_SERVER_0_2_3 =
            MinecraftVersionImpl.of("Alpha server 0.2.3", 3, PRE_NETTY); // Server for a1.2.1
    MinecraftVersion A1_2_1_01 = MinecraftVersionImpl.of("a1.2.1_01", 3, PRE_NETTY);
    MinecraftVersion A1_2_2 = MinecraftVersionImpl.of("a1.2.2", 4, PRE_NETTY);
    MinecraftVersion A_SERVER_0_2_4 =
            MinecraftVersionImpl.of("Alpha server 0.2.4", 4, PRE_NETTY); // Server for a1.2.2
    MinecraftVersion A1_2_3 = MinecraftVersionImpl.of("a1.2.3", 5, PRE_NETTY);
    MinecraftVersion A_SERVER_0_2_5 =
            MinecraftVersionImpl.of("Alpha server 0.2.5", 5, PRE_NETTY); // Server for a1.2.3
    MinecraftVersion A1_2_3_01 = MinecraftVersionImpl.of("a1.2.3_01", 5, PRE_NETTY);
    MinecraftVersion A1_2_3_02 = MinecraftVersionImpl.of("a1.2.3_02", 5, PRE_NETTY);
    MinecraftVersion A_SERVER_0_2_5_01 =
            MinecraftVersionImpl.of("Alpha server 0.2.5_01", 5, PRE_NETTY); // Server for a1.2.3_02
    MinecraftVersion A_SERVER_0_2_5_02 =
            MinecraftVersionImpl.of("Alpha server 0.2.5_02", 5, PRE_NETTY); // Server for a1.2.3_02
    MinecraftVersion A1_2_3_04 = MinecraftVersionImpl.of("a1.2.3_04", 5, PRE_NETTY);
    MinecraftVersion A1_2_3_05 = MinecraftVersionImpl.of("a1.2.3_05", 6, PRE_NETTY);
    MinecraftVersion A_SERVER_0_2_6 =
            MinecraftVersionImpl.of("Alpha server 0.2.6", 6, PRE_NETTY); // Server for a1.2.3_05
    MinecraftVersion A_SERVER_0_2_6_01 =
            MinecraftVersionImpl.of("Alpha server 0.2.6_01", 6, PRE_NETTY); // Server for a1.2.3_05
    MinecraftVersion A1_2_4 = MinecraftVersionImpl.of("a1.2.4", 6, PRE_NETTY);
    MinecraftVersion A_SERVER_0_2_6_02 =
            MinecraftVersionImpl.of("Alpha server 0.2.6_02", 6, PRE_NETTY); // Server for a1.2.4
    MinecraftVersion A1_2_4_01 = MinecraftVersionImpl.of("a1.2.4_01", 6, PRE_NETTY);
    MinecraftVersion A1_2_5 = MinecraftVersionImpl.of("a1.2.5", 6, PRE_NETTY);
    MinecraftVersion A_SERVER_0_2_7 =
            MinecraftVersionImpl.of("Alpha server 0.2.7", 6, PRE_NETTY); // Server for a1.2.5
    MinecraftVersion A1_2_6 = MinecraftVersionImpl.of("a1.2.6", 6, PRE_NETTY);
    MinecraftVersion A_SERVER_0_2_8 =
            MinecraftVersionImpl.of("Alpha server 0.2.8", 6, PRE_NETTY); // Server for a1.2.6
}
