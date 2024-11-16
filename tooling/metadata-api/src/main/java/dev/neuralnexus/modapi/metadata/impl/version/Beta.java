/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata.impl.version;

import static dev.neuralnexus.modapi.metadata.ProtocolType.PRE_NETTY;

import dev.neuralnexus.modapi.metadata.MinecraftVersion;

// 6
@SuppressWarnings("unused")
public interface Beta {
    MinecraftVersion B1_0 = MinecraftVersionImpl.of("b1.0", 7, PRE_NETTY);
    MinecraftVersion B1_0_01 = MinecraftVersionImpl.of("b1.0_01", 7, PRE_NETTY);
    MinecraftVersion B1_0_2 = MinecraftVersionImpl.of("b1.0.2", 7, PRE_NETTY);
    MinecraftVersion B1_1 = MinecraftVersionImpl.of("b1.1", 7, PRE_NETTY);
    MinecraftVersion B1_1_01 = MinecraftVersionImpl.of("b1.1_01", 7, PRE_NETTY);
    MinecraftVersion B1_1_02 = MinecraftVersionImpl.of("b1.1_02", 8, PRE_NETTY);
    MinecraftVersion B1_2 = MinecraftVersionImpl.of("b1.2", 8, PRE_NETTY);
    MinecraftVersion B1_2_01 = MinecraftVersionImpl.of("b1.2_01", 8, PRE_NETTY);
    MinecraftVersion B1_2_02 = MinecraftVersionImpl.of("b1.2_02", 8, PRE_NETTY);
    MinecraftVersion B1_3 = MinecraftVersionImpl.of("b1.3", 9, PRE_NETTY);
    MinecraftVersion B1_3_01 = MinecraftVersionImpl.of("b1.3_01", 9, PRE_NETTY);
    MinecraftVersion B1_4 = MinecraftVersionImpl.of("b1.4", 10, PRE_NETTY);
    MinecraftVersion B1_4_01 = MinecraftVersionImpl.of("b1.4_01", 10, PRE_NETTY);
    MinecraftVersion B1_5 = MinecraftVersionImpl.of("b1.5", 11, PRE_NETTY);
    MinecraftVersion B1_5_01 = MinecraftVersionImpl.of("b1.5_01", 11, PRE_NETTY);
    MinecraftVersion B1_5_02 = MinecraftVersionImpl.of("b1.5_02", 11, PRE_NETTY);
    MinecraftVersion B1_6_P = MinecraftVersionImpl.of("b1.6 (preview)", 13, PRE_NETTY);
    MinecraftVersion B1_6_TEST_BUILD_3 =
            MinecraftVersionImpl.of("b1.6 Test Build 3", 12, PRE_NETTY);
    MinecraftVersion B1_6 = MinecraftVersionImpl.of("b1.6", 13, PRE_NETTY);
    MinecraftVersion B1_6_1 = MinecraftVersionImpl.of("b1.6.1", 13, PRE_NETTY);
    MinecraftVersion B1_6_2 = MinecraftVersionImpl.of("b1.6.2", 13, PRE_NETTY);
    MinecraftVersion B1_6_3 = MinecraftVersionImpl.of("b1.6.3", 13, PRE_NETTY);
    MinecraftVersion B1_6_4 = MinecraftVersionImpl.of("b1.6.4", 13, PRE_NETTY);
    MinecraftVersion B1_6_5 = MinecraftVersionImpl.of("b1.6.5", 13, PRE_NETTY);
    MinecraftVersion B1_6_6 = MinecraftVersionImpl.of("b1.6.6", 13, PRE_NETTY);
    MinecraftVersion B1_7_P = MinecraftVersionImpl.of("b1.7 (preview)", 14, PRE_NETTY);
    MinecraftVersion B1_7 = MinecraftVersionImpl.of("b1.7", 14, PRE_NETTY);
    MinecraftVersion B1_7_01 = MinecraftVersionImpl.of("b1.7_01", 14, PRE_NETTY);
    MinecraftVersion B1_7_2 = MinecraftVersionImpl.of("b1.7.2", 14, PRE_NETTY);
    MinecraftVersion B1_7_3 = MinecraftVersionImpl.of("b1.7.3", 14, PRE_NETTY);
    MinecraftVersion B1_8_PR = MinecraftVersionImpl.of("b1.8 Pre-release", 15, PRE_NETTY);
    MinecraftVersion B1_8_PR2 = MinecraftVersionImpl.of("b1.8 Pre-release 2 ;)", 16, PRE_NETTY);
    MinecraftVersion B1_8 = MinecraftVersionImpl.of("b1.8", 17, PRE_NETTY);
    MinecraftVersion B1_8_1 = MinecraftVersionImpl.of("b1.8.1", 17, PRE_NETTY);
    MinecraftVersion B1_9_PR = MinecraftVersionImpl.of("b1.9 Prerelease", 18, PRE_NETTY);
    MinecraftVersion B1_9_PR2 = MinecraftVersionImpl.of("b1.9 Prerelease 2", 19, PRE_NETTY);
    MinecraftVersion B1_9_PR3 = MinecraftVersionImpl.of("b1.9 Prerelease 3", 19, PRE_NETTY);
    MinecraftVersion B1_9_PR4 = MinecraftVersionImpl.of("b1.9 Prerelease 4", 20, PRE_NETTY);
    MinecraftVersion B1_9_PR5 = MinecraftVersionImpl.of("b1.9 Prerelease 5", 21, PRE_NETTY);
    MinecraftVersion B1_9_PR6 = MinecraftVersionImpl.of("b1.9 Prerelease 6", 22, PRE_NETTY);
    MinecraftVersion RC1 = MinecraftVersionImpl.of("rc1", 22, PRE_NETTY);
    MinecraftVersion RC2 = MinecraftVersionImpl.of("rc2", 22, PRE_NETTY);
}
