/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata.impl.version;

import static dev.neuralnexus.modapi.metadata.ProtocolType.APRIL_FOOLS;
import static dev.neuralnexus.modapi.metadata.ProtocolType.NETTY;
import static dev.neuralnexus.modapi.metadata.ProtocolType.PRE_NETTY;

import dev.neuralnexus.modapi.metadata.MinecraftVersion;

@SuppressWarnings("unused")
public interface Release {
    MinecraftVersion V0 = MinecraftVersionImpl.of("1.0.0", 22, PRE_NETTY);
    MinecraftVersion V0_1 = MinecraftVersionImpl.of("1.0.1", 22, PRE_NETTY);
    MinecraftVersion V11W47A = MinecraftVersionImpl.of("11w47a", 22, PRE_NETTY, true);
    MinecraftVersion V11W48A = MinecraftVersionImpl.of("11w48a", 22, PRE_NETTY, true);
    MinecraftVersion V11W49A = MinecraftVersionImpl.of("11w49a", 22, PRE_NETTY, true);
    MinecraftVersion V11W50A = MinecraftVersionImpl.of("11w50a", 22, PRE_NETTY, true);
    MinecraftVersion V12W01A = MinecraftVersionImpl.of("12w01a", 23, PRE_NETTY, true);
    MinecraftVersion V1 = MinecraftVersionImpl.of("1.1", 23, PRE_NETTY);
    MinecraftVersion V12W03A = MinecraftVersionImpl.of("12w03a", 24, PRE_NETTY, true);
    MinecraftVersion V12W04A = MinecraftVersionImpl.of("12w04a", 24, PRE_NETTY, true);
    MinecraftVersion V12W05A = MinecraftVersionImpl.of("12w05a", 24, PRE_NETTY, true);
    MinecraftVersion V12W05B = MinecraftVersionImpl.of("12w05b", 24, PRE_NETTY, true);
    MinecraftVersion V12W06A = MinecraftVersionImpl.of("12w06a", 25, PRE_NETTY, true);
    MinecraftVersion V12W07A = MinecraftVersionImpl.of("12w07a", 27, PRE_NETTY, true);
    MinecraftVersion V12W07B = MinecraftVersionImpl.of("12w07b", 27, PRE_NETTY, true);
    MinecraftVersion V12W08A = MinecraftVersionImpl.of("12w08a", 28, PRE_NETTY, true);
    MinecraftVersion V2 = MinecraftVersionImpl.of("1.2", 28, PRE_NETTY);
    MinecraftVersion V2_1 = MinecraftVersionImpl.of("1.2.1", 28, PRE_NETTY);
    MinecraftVersion V2_2 = MinecraftVersionImpl.of("1.2.2", 28, PRE_NETTY);
    MinecraftVersion V2_3 = MinecraftVersionImpl.of("1.2.3", 28, PRE_NETTY);
    MinecraftVersion V2_4 = MinecraftVersionImpl.of("1.2.4", 29, PRE_NETTY);
    MinecraftVersion V2_5 = MinecraftVersionImpl.of("1.2.5", 29, PRE_NETTY);
    MinecraftVersion V12W15A = MinecraftVersionImpl.of("12w15a", 29, PRE_NETTY, true);
    MinecraftVersion V12W16A = MinecraftVersionImpl.of("12w16a", 30, PRE_NETTY, true);
    MinecraftVersion V12W17A = MinecraftVersionImpl.of("12w17a", 31, PRE_NETTY, true);
    MinecraftVersion V12W18A = MinecraftVersionImpl.of("12w18a", 32, PRE_NETTY, true);
    MinecraftVersion V12W19A = MinecraftVersionImpl.of("12w19a", 32, PRE_NETTY, true);
    MinecraftVersion V12W21A = MinecraftVersionImpl.of("12w21a", 33, PRE_NETTY, true);
    MinecraftVersion V12W21B = MinecraftVersionImpl.of("12w21b", 33, PRE_NETTY, true);
    MinecraftVersion V12W22A = MinecraftVersionImpl.of("12w22a", 34, PRE_NETTY, true);
    MinecraftVersion V12W23A = MinecraftVersionImpl.of("12w23a", 35, PRE_NETTY, true);
    MinecraftVersion V12W23B = MinecraftVersionImpl.of("12w23b", 35, PRE_NETTY, true);
    MinecraftVersion V12W24A = MinecraftVersionImpl.of("12w24a", 36, PRE_NETTY, true);
    MinecraftVersion V12W25A = MinecraftVersionImpl.of("12w25a", 37, PRE_NETTY, true);
    MinecraftVersion V12W26A = MinecraftVersionImpl.of("12w26a", 37, PRE_NETTY, true);
    MinecraftVersion V12W27A = MinecraftVersionImpl.of("12w27a", 38, PRE_NETTY, true);
    MinecraftVersion V12W30A = MinecraftVersionImpl.of("12w30a", 38, PRE_NETTY, true);
    MinecraftVersion V12W30B = MinecraftVersionImpl.of("12w30b", 38, PRE_NETTY, true);
    MinecraftVersion V12W30C = MinecraftVersionImpl.of("12w30c", 39, PRE_NETTY, true);
    MinecraftVersion V12W30D = MinecraftVersionImpl.of("12w30d", 39, PRE_NETTY, true);
    MinecraftVersion V12W30E = MinecraftVersionImpl.of("12w30e", 39, PRE_NETTY, true);
    MinecraftVersion V3 = MinecraftVersionImpl.of("1.3", 39, PRE_NETTY);
    MinecraftVersion V3_1 = MinecraftVersionImpl.of("1.3.1", 39, PRE_NETTY);
    MinecraftVersion V3_2 = MinecraftVersionImpl.of("1.3.2", 39, PRE_NETTY);
    MinecraftVersion V12W32A = MinecraftVersionImpl.of("12w32a", 40, PRE_NETTY, true);
    MinecraftVersion V12W34A = MinecraftVersionImpl.of("12w34a", 41, PRE_NETTY, true);
    MinecraftVersion V12W34B = MinecraftVersionImpl.of("12w34b", 42, PRE_NETTY, true);
    MinecraftVersion V12W36A = MinecraftVersionImpl.of("12w36a", 42, PRE_NETTY, true);
    MinecraftVersion V12W37A = MinecraftVersionImpl.of("12w37a", 42, PRE_NETTY, true);
    MinecraftVersion V12W38A = MinecraftVersionImpl.of("12w38a", 43, PRE_NETTY, true);
    MinecraftVersion V12W38B = MinecraftVersionImpl.of("12w38b", 43, PRE_NETTY, true);
    MinecraftVersion V12W39A = MinecraftVersionImpl.of("12w39a", 43, PRE_NETTY, true);
    MinecraftVersion V12W39B = MinecraftVersionImpl.of("12w39b", 43, PRE_NETTY, true);
    MinecraftVersion V12W40A = MinecraftVersionImpl.of("12w40a", 44, PRE_NETTY, true);
    MinecraftVersion V12W40B = MinecraftVersionImpl.of("12w40b", 45, PRE_NETTY, true);
    MinecraftVersion V12W41A = MinecraftVersionImpl.of("12w41a", 46, PRE_NETTY, true);
    MinecraftVersion V12W41B = MinecraftVersionImpl.of("12w41b", 46, PRE_NETTY, true);
    MinecraftVersion V12W42A = MinecraftVersionImpl.of("12w42a", 46, PRE_NETTY, true);
    MinecraftVersion V12W42B = MinecraftVersionImpl.of("12w42b", 47, PRE_NETTY, true);
    MinecraftVersion V4 = MinecraftVersionImpl.of("1.4", 47, PRE_NETTY);
    MinecraftVersion V4_1 = MinecraftVersionImpl.of("1.4.1", 47, PRE_NETTY);
    MinecraftVersion V4_2 = MinecraftVersionImpl.of("1.4.2", 47, PRE_NETTY);
    MinecraftVersion V4_3 = MinecraftVersionImpl.of("1.4.3", 48, PRE_NETTY);
    MinecraftVersion V4_4 = MinecraftVersionImpl.of("1.4.4", 49, PRE_NETTY);
    MinecraftVersion V4_5 = MinecraftVersionImpl.of("1.4.5", 49, PRE_NETTY);
    MinecraftVersion V12W49A = MinecraftVersionImpl.of("12w49a", 50, PRE_NETTY, true);
    MinecraftVersion V12W50A = MinecraftVersionImpl.of("12w50a", 51, PRE_NETTY, true);
    MinecraftVersion V12W50B = MinecraftVersionImpl.of("12w50b", 51, PRE_NETTY, true);
    MinecraftVersion V4_6 = MinecraftVersionImpl.of("1.4.6", 51, PRE_NETTY);
    MinecraftVersion V4_7 = MinecraftVersionImpl.of("1.4.7", 51, PRE_NETTY);
    MinecraftVersion V13W01A = MinecraftVersionImpl.of("13w01a", 52, PRE_NETTY, true);
    MinecraftVersion V13W01B = MinecraftVersionImpl.of("13w01b", 52, PRE_NETTY, true);
    MinecraftVersion V13W02A = MinecraftVersionImpl.of("13w02a", 53, PRE_NETTY, true);
    MinecraftVersion V13W02B = MinecraftVersionImpl.of("13w02b", 53, PRE_NETTY, true);
    MinecraftVersion V13W03A = MinecraftVersionImpl.of("13w03a", 54, PRE_NETTY, true);
    MinecraftVersion V13W04A = MinecraftVersionImpl.of("13w04a", 55, PRE_NETTY, true);
    MinecraftVersion V13W05A = MinecraftVersionImpl.of("13w05a", 56, PRE_NETTY, true);
    MinecraftVersion V13W05B = MinecraftVersionImpl.of("13w05b", 56, PRE_NETTY, true);
    MinecraftVersion V13W06A = MinecraftVersionImpl.of("13w06a", 58, PRE_NETTY, true);
    MinecraftVersion V13W07A = MinecraftVersionImpl.of("13w07a", 58, PRE_NETTY, true);
    MinecraftVersion V13W09A = MinecraftVersionImpl.of("13w09a", 59, PRE_NETTY, true);
    MinecraftVersion V13W09B = MinecraftVersionImpl.of("13w09b", 59, PRE_NETTY, true);
    MinecraftVersion V13W09C = MinecraftVersionImpl.of("13w09c", 60, PRE_NETTY, true);
    MinecraftVersion V13W10A = MinecraftVersionImpl.of("13w10a", 60, PRE_NETTY, true);
    MinecraftVersion V13W10B = MinecraftVersionImpl.of("13w10b", 60, PRE_NETTY, true);
    MinecraftVersion V5 = MinecraftVersionImpl.of("1.5", 60, PRE_NETTY);
    MinecraftVersion V13W11A = MinecraftVersionImpl.of("13w11a", 60, PRE_NETTY, true);
    MinecraftVersion V13W12 = MinecraftVersionImpl.of("13w12~", 60, PRE_NETTY, true);
    MinecraftVersion V5_1 = MinecraftVersionImpl.of("1.5.1", 60, PRE_NETTY);
    MinecraftVersion V13W14A_V2 =
            MinecraftVersionImpl.of(
                    "13w14a (v2.0) Protocol(Blue: 90, Red: 91, Purple: 92", -1, APRIL_FOOLS, true);
    MinecraftVersion V5_2 = MinecraftVersionImpl.of("1.5.2", 61, PRE_NETTY);
    MinecraftVersion V13W16A = MinecraftVersionImpl.of("13w16a", 62, PRE_NETTY, true);
    MinecraftVersion V13W16B = MinecraftVersionImpl.of("13w16b", 63, PRE_NETTY, true);
    MinecraftVersion V13W17A = MinecraftVersionImpl.of("13w17a", 64, PRE_NETTY, true);
    MinecraftVersion V13W18A = MinecraftVersionImpl.of("13w18a", 65, PRE_NETTY, true);
    MinecraftVersion V13W18B = MinecraftVersionImpl.of("13w18b", 65, PRE_NETTY, true);
    MinecraftVersion V13W18C = MinecraftVersionImpl.of("13w18c", 65, PRE_NETTY, true);
    MinecraftVersion V13W19A = MinecraftVersionImpl.of("13w19a", 66, PRE_NETTY, true);
    MinecraftVersion V13W21A = MinecraftVersionImpl.of("13w21a", 67, PRE_NETTY, true);
    MinecraftVersion V13W21B = MinecraftVersionImpl.of("13w21b", 67, PRE_NETTY, true);
    MinecraftVersion V13W22A = MinecraftVersionImpl.of("13w22a", 67, PRE_NETTY, true);
    MinecraftVersion V13W23A = MinecraftVersionImpl.of("13w23a", 67, PRE_NETTY, true);
    MinecraftVersion V13W23B = MinecraftVersionImpl.of("13w23b", 68, PRE_NETTY, true);
    MinecraftVersion V13W24A = MinecraftVersionImpl.of("13w24a", 69, PRE_NETTY, true);
    MinecraftVersion V13W24B = MinecraftVersionImpl.of("13w24b", 70, PRE_NETTY, true);
    MinecraftVersion V13W25A = MinecraftVersionImpl.of("13w25a", 71, PRE_NETTY, true);
    MinecraftVersion V13W25B = MinecraftVersionImpl.of("13w25b", 71, PRE_NETTY, true);
    MinecraftVersion V13W25C = MinecraftVersionImpl.of("13w25c", 71, PRE_NETTY, true);
    MinecraftVersion V13W26A = MinecraftVersionImpl.of("13w26a", 72, PRE_NETTY, true);
    MinecraftVersion V6 = MinecraftVersionImpl.of("1.6", 72, PRE_NETTY);
    MinecraftVersion V6_1 = MinecraftVersionImpl.of("1.6.1", 73, PRE_NETTY);
    MinecraftVersion V6_2 = MinecraftVersionImpl.of("1.6.2", 74, PRE_NETTY);
    MinecraftVersion V13W36A = MinecraftVersionImpl.of("13w36a", 75, PRE_NETTY, true);
    MinecraftVersion V13W36B = MinecraftVersionImpl.of("13w36b", 75, PRE_NETTY, true);
    MinecraftVersion V13W37A = MinecraftVersionImpl.of("13w37a", 76, PRE_NETTY, true);
    MinecraftVersion V13W37B = MinecraftVersionImpl.of("13w37b", 76, PRE_NETTY, true);
    MinecraftVersion V6_3 = MinecraftVersionImpl.of("1.6.3", 77, PRE_NETTY);
    MinecraftVersion V6_4 = MinecraftVersionImpl.of("1.6.4", 78, PRE_NETTY);
    MinecraftVersion V13W38A = MinecraftVersionImpl.of("13w38a", 79, PRE_NETTY, true);
    MinecraftVersion V13W38B = MinecraftVersionImpl.of("13w38b", 79, PRE_NETTY, true);
    MinecraftVersion V13W38C = MinecraftVersionImpl.of("13w38c", 79, PRE_NETTY, true);
    MinecraftVersion V13W39A = MinecraftVersionImpl.of("13w39a", 80, PRE_NETTY, true);
    MinecraftVersion V13W39B = MinecraftVersionImpl.of("13w39b", 80, PRE_NETTY, true);
    MinecraftVersion V13W41A = MinecraftVersionImpl.of("13w41a", 0, NETTY, true);
    MinecraftVersion V13W41B = MinecraftVersionImpl.of("13w41b", 0, NETTY, true);
    MinecraftVersion V13W42A = MinecraftVersionImpl.of("13w42a", 1, NETTY, true);
    MinecraftVersion V13W42B = MinecraftVersionImpl.of("13w42b", 1, NETTY, true);
    MinecraftVersion V13W43A = MinecraftVersionImpl.of("13w43a", 2, NETTY, true);
    MinecraftVersion V7 = MinecraftVersionImpl.of("1.7", 3, NETTY);
    MinecraftVersion V7_1 = MinecraftVersionImpl.of("1.7.1", 3, NETTY);
    MinecraftVersion V7_2 = MinecraftVersionImpl.of("1.7.2", 4, NETTY);
    MinecraftVersion V13W47A = MinecraftVersionImpl.of("13w47a", 4, NETTY, true);
    MinecraftVersion V13W47B = MinecraftVersionImpl.of("13w47b", 4, NETTY, true);
    MinecraftVersion V13W47C = MinecraftVersionImpl.of("13w47c", 4, NETTY, true);
    MinecraftVersion V13W47D = MinecraftVersionImpl.of("13w47d", 4, NETTY, true);
    MinecraftVersion V13W47E = MinecraftVersionImpl.of("13w47e", 4, NETTY, true);
    MinecraftVersion V13W48A = MinecraftVersionImpl.of("13w48a", 4, NETTY, true);
    MinecraftVersion V13W48B = MinecraftVersionImpl.of("13w48b", 4, NETTY, true);
    MinecraftVersion V13W49A = MinecraftVersionImpl.of("13w49a", 4, NETTY, true);
    MinecraftVersion V7_3 = MinecraftVersionImpl.of("1.7.3", 4, NETTY);
    MinecraftVersion V7_4 = MinecraftVersionImpl.of("1.7.4", 4, NETTY);
    MinecraftVersion V7_5 = MinecraftVersionImpl.of("1.7.5", 4, NETTY);
    MinecraftVersion V7_6_PRE1 = MinecraftVersionImpl.of("1.7.6-pre1", 5, NETTY, true);
    MinecraftVersion V7_6_PRE2 = MinecraftVersionImpl.of("1.7.6-pre2", 5, NETTY, true);
    MinecraftVersion V7_6 = MinecraftVersionImpl.of("1.7.6", 5, NETTY);
    MinecraftVersion V7_7 = MinecraftVersionImpl.of("1.7.7", 5, NETTY);
    MinecraftVersion V7_8 = MinecraftVersionImpl.of("1.7.8", 5, NETTY);
    MinecraftVersion V7_9 = MinecraftVersionImpl.of("1.7.9", 5, NETTY);
    MinecraftVersion V7_10_PRE1 = MinecraftVersionImpl.of("1.7.10-pre1", 5, NETTY, true);
    MinecraftVersion V7_10_PRE2 = MinecraftVersionImpl.of("1.7.10-pre2", 5, NETTY, true);
    MinecraftVersion V7_10_PRE3 = MinecraftVersionImpl.of("1.7.10-pre3", 5, NETTY, true);
    MinecraftVersion V7_10_PRE4 = MinecraftVersionImpl.of("1.7.10-pre4", 5, NETTY, true);
    MinecraftVersion V7_10 = MinecraftVersionImpl.of("1.7.10", 5, NETTY);
    MinecraftVersion V14W02A = MinecraftVersionImpl.of("14w02a", 5, NETTY, true);
    MinecraftVersion V14W02B = MinecraftVersionImpl.of("14w02b", 5, NETTY, true);
    MinecraftVersion V14W02C = MinecraftVersionImpl.of("14w02c", 5, NETTY, true);
    MinecraftVersion V14W03A = MinecraftVersionImpl.of("14w03a", 6, NETTY, true);
    MinecraftVersion V14W03B = MinecraftVersionImpl.of("14w03b", 6, NETTY, true);
    MinecraftVersion V14W04A = MinecraftVersionImpl.of("14w04a", 7, NETTY, true);
    MinecraftVersion V14W04B = MinecraftVersionImpl.of("14w04b", 8, NETTY, true);
    MinecraftVersion V14W05A = MinecraftVersionImpl.of("14w05a", 9, NETTY, true);
    MinecraftVersion V14W05B = MinecraftVersionImpl.of("14w05b", 9, NETTY, true);
    MinecraftVersion V14W06A = MinecraftVersionImpl.of("14w06a", 10, NETTY, true);
    MinecraftVersion V14W06B = MinecraftVersionImpl.of("14w06b", 10, NETTY, true);
    MinecraftVersion V14W07A = MinecraftVersionImpl.of("14w07a", 11, NETTY, true);
    MinecraftVersion V14W08A = MinecraftVersionImpl.of("14w08a", 12, NETTY, true);
    MinecraftVersion V14W10A = MinecraftVersionImpl.of("14w10a", 13, NETTY, true);
    MinecraftVersion V14W10B = MinecraftVersionImpl.of("14w10b", 13, NETTY, true);
    MinecraftVersion V14W10C = MinecraftVersionImpl.of("14w10c", 13, NETTY, true);
    MinecraftVersion V14W11A = MinecraftVersionImpl.of("14w11a", 14, NETTY, true);
    MinecraftVersion V14W11B = MinecraftVersionImpl.of("14w11b", 14, NETTY, true);
    MinecraftVersion V14W17A = MinecraftVersionImpl.of("14w17a", 15, NETTY, true);
    MinecraftVersion V14W18A = MinecraftVersionImpl.of("14w18a", 16, NETTY, true);
    MinecraftVersion V14W18B = MinecraftVersionImpl.of("14w18b", 16, NETTY, true);
    MinecraftVersion V14W19A = MinecraftVersionImpl.of("14w19a", 17, NETTY, true);
    MinecraftVersion V14W20A = MinecraftVersionImpl.of("14w20a", 18, NETTY, true);
    MinecraftVersion V14W20B = MinecraftVersionImpl.of("14w20b", 18, NETTY, true);
    MinecraftVersion V14W21A = MinecraftVersionImpl.of("14w21a", 19, NETTY, true);
    MinecraftVersion V14W21B = MinecraftVersionImpl.of("14w21b", 20, NETTY, true);
    MinecraftVersion V14W25A = MinecraftVersionImpl.of("14w25a", 21, NETTY, true);
    MinecraftVersion V14W25B = MinecraftVersionImpl.of("14w25b", 22, NETTY, true);
    MinecraftVersion V14W26A = MinecraftVersionImpl.of("14w26a", 23, NETTY, true);
    MinecraftVersion V14W26B = MinecraftVersionImpl.of("14w26b", 24, NETTY, true);
    MinecraftVersion V14W26C = MinecraftVersionImpl.of("14w26c", 25, NETTY, true);
    MinecraftVersion V14W27A = MinecraftVersionImpl.of("14w27a", 26, NETTY, true);
    MinecraftVersion V14W27B = MinecraftVersionImpl.of("14w27b", 26, NETTY, true);
    MinecraftVersion V14W28A = MinecraftVersionImpl.of("14w28a", 27, NETTY, true);
    MinecraftVersion V14W28B = MinecraftVersionImpl.of("14w28b", 28, NETTY, true);
    MinecraftVersion V14W29A = MinecraftVersionImpl.of("14w29a", 29, NETTY, true);
    MinecraftVersion V14W29B = MinecraftVersionImpl.of("14w29b", 29, NETTY, true);
    MinecraftVersion V14W30A = MinecraftVersionImpl.of("14w30a", 30, NETTY, true);
    MinecraftVersion V14W30B = MinecraftVersionImpl.of("14w30b", 30, NETTY, true);
    MinecraftVersion V14W30C = MinecraftVersionImpl.of("14w30c", 31, NETTY, true);
    MinecraftVersion V14W31A = MinecraftVersionImpl.of("14w31a", 32, NETTY, true);
    MinecraftVersion V14W32A = MinecraftVersionImpl.of("14w32a", 33, NETTY, true);
    MinecraftVersion V14W32B = MinecraftVersionImpl.of("14w32b", 34, NETTY, true);
    MinecraftVersion V14W32C = MinecraftVersionImpl.of("14w32c", 35, NETTY, true);
    MinecraftVersion V14W32D = MinecraftVersionImpl.of("14w32d", 36, NETTY, true);
    MinecraftVersion V14W33A = MinecraftVersionImpl.of("14w33a", 37, NETTY, true);
    MinecraftVersion V14W33B = MinecraftVersionImpl.of("14w33b", 38, NETTY, true);
    MinecraftVersion V14W33C = MinecraftVersionImpl.of("14w33c", 39, NETTY, true);
    MinecraftVersion V14W34A = MinecraftVersionImpl.of("14w34a", 40, NETTY, true);
    MinecraftVersion V14W34B = MinecraftVersionImpl.of("14w34b", 41, NETTY, true);
    MinecraftVersion V14W34C = MinecraftVersionImpl.of("14w34c", 42, NETTY, true);
    MinecraftVersion V14W34D = MinecraftVersionImpl.of("14w34d", 43, NETTY, true);
    MinecraftVersion V8_PRE1 = MinecraftVersionImpl.of("1.8-pre1", 44, NETTY, true);
    MinecraftVersion V8_PRE2 = MinecraftVersionImpl.of("1.8-pre2", 45, NETTY, true);
    MinecraftVersion V8_PRE3 = MinecraftVersionImpl.of("1.8-pre3", 46, NETTY, true);
    MinecraftVersion V8 = MinecraftVersionImpl.of("1.8", 47, NETTY);
    MinecraftVersion V8_1_PRE1 = MinecraftVersionImpl.of("1.8.1-pre1", 47, NETTY, true);
    MinecraftVersion V8_1_PRE2 = MinecraftVersionImpl.of("1.8.1-pre2", 47, NETTY, true);
    MinecraftVersion V8_1_PRE3 = MinecraftVersionImpl.of("1.8.1-pre3", 47, NETTY, true);
    MinecraftVersion V8_1_PRE4 = MinecraftVersionImpl.of("1.8.1-pre4", 47, NETTY, true);
    MinecraftVersion V8_1_PRE5 = MinecraftVersionImpl.of("1.8.1-pre5", 47, NETTY, true);
    MinecraftVersion V8_1 = MinecraftVersionImpl.of("1.8.1", 47, NETTY);
    MinecraftVersion V8_2_PRE1 = MinecraftVersionImpl.of("1.8.2-pre1", 47, NETTY, true);
    MinecraftVersion V8_2_PRE2 = MinecraftVersionImpl.of("1.8.2-pre2", 47, NETTY, true);
    MinecraftVersion V8_2_PRE3 = MinecraftVersionImpl.of("1.8.2-pre3", 47, NETTY, true);
    MinecraftVersion V8_2_PRE4 = MinecraftVersionImpl.of("1.8.2-pre4", 47, NETTY, true);
    MinecraftVersion V8_2_PRE5 = MinecraftVersionImpl.of("1.8.2-pre5", 47, NETTY, true);
    MinecraftVersion V8_2_PRE6 = MinecraftVersionImpl.of("1.8.2-pre6", 47, NETTY, true);
    MinecraftVersion V8_2_PRE7 = MinecraftVersionImpl.of("1.8.2-pre7", 47, NETTY, true);
    MinecraftVersion V8_2 = MinecraftVersionImpl.of("1.8.2", 47, NETTY);
    MinecraftVersion V8_3 = MinecraftVersionImpl.of("1.8.3", 47, NETTY);
    MinecraftVersion V15W14A = MinecraftVersionImpl.of("15w14a", 48, APRIL_FOOLS, true);
    MinecraftVersion V8_4 = MinecraftVersionImpl.of("1.8.4", 47, NETTY);
    MinecraftVersion V8_5 = MinecraftVersionImpl.of("1.8.5", 47, NETTY);
    MinecraftVersion V8_6 = MinecraftVersionImpl.of("1.8.6", 47, NETTY);
    MinecraftVersion V8_7 = MinecraftVersionImpl.of("1.8.7", 47, NETTY);
    MinecraftVersion V8_8 = MinecraftVersionImpl.of("1.8.8", 47, NETTY);
    MinecraftVersion V8_9 = MinecraftVersionImpl.of("1.8.9", 47, NETTY);
    MinecraftVersion V15W31A = MinecraftVersionImpl.of("15w31a", 49, NETTY, true);
    MinecraftVersion V15W31B = MinecraftVersionImpl.of("15w31b", 50, NETTY, true);
    MinecraftVersion V15W31C = MinecraftVersionImpl.of("15w31c", 51, NETTY, true);
    MinecraftVersion V15W32A = MinecraftVersionImpl.of("15w32a", 52, NETTY, true);
    MinecraftVersion V15W32B = MinecraftVersionImpl.of("15w32b", 53, NETTY, true);
    MinecraftVersion V15W32C = MinecraftVersionImpl.of("15w32c", 54, NETTY, true);
    MinecraftVersion V15W33A = MinecraftVersionImpl.of("15w33a", 55, NETTY, true);
    MinecraftVersion V15W33B = MinecraftVersionImpl.of("15w33b", 56, NETTY, true);
    MinecraftVersion V15W33C = MinecraftVersionImpl.of("15w33c", 57, NETTY, true);
    MinecraftVersion V15W34A = MinecraftVersionImpl.of("15w34a", 58, NETTY, true);
    MinecraftVersion V15W34B = MinecraftVersionImpl.of("15w34b", 59, NETTY, true);
    MinecraftVersion V15W34C = MinecraftVersionImpl.of("15w34c", 60, NETTY, true);
    MinecraftVersion V15W34D = MinecraftVersionImpl.of("15w34d", 61, NETTY, true);
    MinecraftVersion V15W35A = MinecraftVersionImpl.of("15w35a", 62, NETTY, true);
    MinecraftVersion V15W35B = MinecraftVersionImpl.of("15w35b", 63, NETTY, true);
    MinecraftVersion V15W35C = MinecraftVersionImpl.of("15w35c", 64, NETTY, true);
    MinecraftVersion V15W35D = MinecraftVersionImpl.of("15w35d", 65, NETTY, true);
    MinecraftVersion V15W35E = MinecraftVersionImpl.of("15w35e", 66, NETTY, true);
    MinecraftVersion V15W36A = MinecraftVersionImpl.of("15w36a", 67, NETTY, true);
    MinecraftVersion V15W36B = MinecraftVersionImpl.of("15w36b", 68, NETTY, true);
    MinecraftVersion V15W36C = MinecraftVersionImpl.of("15w36c", 69, NETTY, true);
    MinecraftVersion V15W36D = MinecraftVersionImpl.of("15w36d", 70, NETTY, true);
    MinecraftVersion V15W37A = MinecraftVersionImpl.of("15w37a", 71, NETTY, true);
    MinecraftVersion V15W38A = MinecraftVersionImpl.of("15w38a", 72, NETTY, true);
    MinecraftVersion V15W38B = MinecraftVersionImpl.of("15w38b", 73, NETTY, true);
    MinecraftVersion V15W39A = MinecraftVersionImpl.of("15w39a", 74, NETTY, true);
    MinecraftVersion V15W39B = MinecraftVersionImpl.of("15w39b", 74, NETTY, true);
    MinecraftVersion V15W39C = MinecraftVersionImpl.of("15w39c", 74, NETTY, true);
    MinecraftVersion V15W40A = MinecraftVersionImpl.of("15w40a", 75, NETTY, true);
    MinecraftVersion V15W41A = MinecraftVersionImpl.of("15w41a", 77, NETTY, true);
    MinecraftVersion V15W41B = MinecraftVersionImpl.of("15w41b", 78, NETTY, true);
    MinecraftVersion V15W42A = MinecraftVersionImpl.of("15w42a", 79, NETTY, true);
    MinecraftVersion V15W43A = MinecraftVersionImpl.of("15w43a", 80, NETTY, true);
    MinecraftVersion V15W43B = MinecraftVersionImpl.of("15w43b", 81, NETTY, true);
    MinecraftVersion V15W43C = MinecraftVersionImpl.of("15w43c", 82, NETTY, true);
    MinecraftVersion V15W44A = MinecraftVersionImpl.of("15w44a", 83, NETTY, true);
    MinecraftVersion V15W44B = MinecraftVersionImpl.of("15w44b", 84, NETTY, true);
    MinecraftVersion V15W45A = MinecraftVersionImpl.of("15w45a", 85, NETTY, true);
    MinecraftVersion V15W46A = MinecraftVersionImpl.of("15w46a", 86, NETTY, true);
    MinecraftVersion V15W47A = MinecraftVersionImpl.of("15w47a", 87, NETTY, true);
    MinecraftVersion V15W47B = MinecraftVersionImpl.of("15w47b", 88, NETTY, true);
    MinecraftVersion V15W47C = MinecraftVersionImpl.of("15w47c", 89, NETTY, true);
    MinecraftVersion V15W49A = MinecraftVersionImpl.of("15w49a", 90, NETTY, true);
    MinecraftVersion V15W49B = MinecraftVersionImpl.of("15w49b", 91, NETTY, true);
    MinecraftVersion V15W50A = MinecraftVersionImpl.of("15w50a", 92, NETTY, true);
    MinecraftVersion V15W40B = MinecraftVersionImpl.of("15w40b", 76, NETTY, true);
    MinecraftVersion V15W51A = MinecraftVersionImpl.of("15w51a", 93, NETTY, true);
    MinecraftVersion V15W51B = MinecraftVersionImpl.of("15w51b", 94, NETTY, true);
    MinecraftVersion V16W02A = MinecraftVersionImpl.of("16w02a", 95, NETTY, true);
    MinecraftVersion V16W03A = MinecraftVersionImpl.of("16w03a", 96, NETTY, true);
    MinecraftVersion V16W04A = MinecraftVersionImpl.of("16w04a", 97, NETTY, true);
    MinecraftVersion V16W05A = MinecraftVersionImpl.of("16w05a", 98, NETTY, true);
    MinecraftVersion V16W05B = MinecraftVersionImpl.of("16w05b", 99, NETTY, true);
    MinecraftVersion V16W06A = MinecraftVersionImpl.of("16w06a", 100, NETTY, true);
    MinecraftVersion V16W07A = MinecraftVersionImpl.of("16w07a", 101, NETTY, true);
    MinecraftVersion V16W07B = MinecraftVersionImpl.of("16w07b", 102, NETTY, true);
    MinecraftVersion V9_PRE1 = MinecraftVersionImpl.of("1.9-pre1", 103, NETTY, true);
    MinecraftVersion V9_PRE2 = MinecraftVersionImpl.of("1.9-pre2", 104, NETTY, true);
    MinecraftVersion V9_PRE3 = MinecraftVersionImpl.of("1.9-pre3", 105, NETTY, true);
    MinecraftVersion V9_PRE4 = MinecraftVersionImpl.of("1.9-pre4", 106, NETTY, true);
    MinecraftVersion V9 = MinecraftVersionImpl.of("1.9", 107, NETTY);
    MinecraftVersion V9_1_PRE1 = MinecraftVersionImpl.of("1.9.1-pre1", 107, NETTY, true);
    MinecraftVersion V9_1_PRE2 = MinecraftVersionImpl.of("1.9.1-pre2", 108, NETTY, true);
    MinecraftVersion V9_1_PRE3 = MinecraftVersionImpl.of("1.9.1-pre3", 108, NETTY, true);
    MinecraftVersion V9_1 = MinecraftVersionImpl.of("1.9.1", 108, NETTY);
    MinecraftVersion V1RV_PRE1 = MinecraftVersionImpl.of("1.RV-Pre1", 108, APRIL_FOOLS, true);
    MinecraftVersion V16W14A = MinecraftVersionImpl.of("16w14a", 109, NETTY, true);
    MinecraftVersion V16W15A = MinecraftVersionImpl.of("16w15a", 109, NETTY, true);
    MinecraftVersion V16W15B = MinecraftVersionImpl.of("16w15b", 109, NETTY, true);
    MinecraftVersion V9_3_PRE1 = MinecraftVersionImpl.of("1.9.3-pre1", 109, NETTY, true);
    MinecraftVersion V9_3_PRE2 = MinecraftVersionImpl.of("1.9.3-pre2", 110, NETTY, true);
    MinecraftVersion V9_3_PRE3 = MinecraftVersionImpl.of("1.9.3-pre3", 110, NETTY, true);
    MinecraftVersion V9_3 = MinecraftVersionImpl.of("1.9.3", 110, NETTY);
    MinecraftVersion V9_4 = MinecraftVersionImpl.of("1.9.4", 110, NETTY);
    MinecraftVersion V16W20A = MinecraftVersionImpl.of("16w20a", 201, NETTY, true);
    MinecraftVersion V16W21A = MinecraftVersionImpl.of("16w21a", 202, NETTY, true);
    MinecraftVersion V16W21B = MinecraftVersionImpl.of("16w21b", 203, NETTY, true);
    MinecraftVersion V10_PRE1 = MinecraftVersionImpl.of("1.10-pre1", 204, NETTY, true);
    MinecraftVersion V10_PRE2 = MinecraftVersionImpl.of("1.10-pre2", 205, NETTY, true);
    MinecraftVersion V10 = MinecraftVersionImpl.of("1.10", 210, NETTY);
    MinecraftVersion V10_1 = MinecraftVersionImpl.of("1.10.1", 210, NETTY);
    MinecraftVersion V10_2 = MinecraftVersionImpl.of("1.10.2", 210, NETTY);
    MinecraftVersion V16W32A = MinecraftVersionImpl.of("16w32a", 301, NETTY, true);
    MinecraftVersion V16W32B = MinecraftVersionImpl.of("16w32b", 302, NETTY, true);
    MinecraftVersion V16W33A = MinecraftVersionImpl.of("16w33a", 303, NETTY, true);
    MinecraftVersion V16W35A = MinecraftVersionImpl.of("16w35a", 304, NETTY, true);
    MinecraftVersion V16W36A = MinecraftVersionImpl.of("16w36a", 305, NETTY, true);
    MinecraftVersion V16W38A = MinecraftVersionImpl.of("16w38a", 306, NETTY, true);
    MinecraftVersion V16W39A = MinecraftVersionImpl.of("16w39a", 307, NETTY, true);
    MinecraftVersion V16W39B = MinecraftVersionImpl.of("16w39b", 308, NETTY, true);
    MinecraftVersion V16W39C = MinecraftVersionImpl.of("16w39c", 309, NETTY, true);
    MinecraftVersion V16W40A = MinecraftVersionImpl.of("16w40a", 310, NETTY, true);
    MinecraftVersion V16W41A = MinecraftVersionImpl.of("16w41a", 311, NETTY, true);
    MinecraftVersion V16W42A = MinecraftVersionImpl.of("16w42a", 312, NETTY, true);
    MinecraftVersion V16W43A = MinecraftVersionImpl.of("16w43a", 313, NETTY, true);
    MinecraftVersion V16W44A = MinecraftVersionImpl.of("16w44a", 313, NETTY, true);
    MinecraftVersion V11_PRE1 = MinecraftVersionImpl.of("1.11-pre1", 314, NETTY, true);
    MinecraftVersion V11 = MinecraftVersionImpl.of("1.11", 315, NETTY);
    MinecraftVersion V16W50A = MinecraftVersionImpl.of("16w50a", 316, NETTY, true);
    MinecraftVersion V11_1 = MinecraftVersionImpl.of("1.11.1", 316, NETTY);
    MinecraftVersion V11_2 = MinecraftVersionImpl.of("1.11.2", 316, NETTY);
    MinecraftVersion V17W06A = MinecraftVersionImpl.of("17w06a", 317, NETTY, true);
    MinecraftVersion V17W13A = MinecraftVersionImpl.of("17w13a", 318, NETTY, true);
    MinecraftVersion V17W13B = MinecraftVersionImpl.of("17w13b", 319, NETTY, true);
    MinecraftVersion V17W14A = MinecraftVersionImpl.of("17w14a", 320, NETTY, true);
    MinecraftVersion V17W15A = MinecraftVersionImpl.of("17w15a", 321, NETTY, true);
    MinecraftVersion V17W16A = MinecraftVersionImpl.of("17w16a", 322, NETTY, true);
    MinecraftVersion V17W16B = MinecraftVersionImpl.of("17w16b", 323, NETTY, true);
    MinecraftVersion V17W17A = MinecraftVersionImpl.of("17w17a", 324, NETTY, true);
    MinecraftVersion V17W17B = MinecraftVersionImpl.of("17w17b", 325, NETTY, true);
    MinecraftVersion V17W18A = MinecraftVersionImpl.of("17w18a", 326, NETTY, true);
    MinecraftVersion V17W18B = MinecraftVersionImpl.of("17w18b", 327, NETTY, true);
    MinecraftVersion V12_PRE1 = MinecraftVersionImpl.of("1.12-pre1", 328, NETTY, true);
    MinecraftVersion V12_PRE2 = MinecraftVersionImpl.of("1.12-pre2", 329, NETTY, true);
    MinecraftVersion V12_PRE3 = MinecraftVersionImpl.of("1.12-pre3", 330, NETTY, true);
    MinecraftVersion V12_PRE4 = MinecraftVersionImpl.of("1.12-pre4", 331, NETTY, true);
    MinecraftVersion V12_PRE5 = MinecraftVersionImpl.of("1.12-pre5", 332, NETTY, true);
    MinecraftVersion V12_PRE6 = MinecraftVersionImpl.of("1.12-pre6", 333, NETTY, true);
    MinecraftVersion V12_PRE7 = MinecraftVersionImpl.of("1.12-pre7", 334, NETTY, true);
    MinecraftVersion V12 = MinecraftVersionImpl.of("1.12", 335, NETTY);
    MinecraftVersion V17W31A = MinecraftVersionImpl.of("17w31a", 336, NETTY, true);
    MinecraftVersion V12_1_PRE1 = MinecraftVersionImpl.of("1.12.1-pre1", 337, NETTY, true);
    MinecraftVersion V12_1 = MinecraftVersionImpl.of("1.12.1", 338, NETTY);
    MinecraftVersion V12_2_PRE1 = MinecraftVersionImpl.of("1.12.2-pre1", 339, NETTY, true);
    MinecraftVersion V12_2_PRE2 = MinecraftVersionImpl.of("1.12.2-pre2", 339, NETTY, true);
    MinecraftVersion V12_2 = MinecraftVersionImpl.of("1.12.2", 340, NETTY);
    MinecraftVersion V17W43A = MinecraftVersionImpl.of("17w43a", 341, NETTY, true);
    MinecraftVersion V17W43B = MinecraftVersionImpl.of("17w43b", 342, NETTY, true);
    MinecraftVersion V17W45A = MinecraftVersionImpl.of("17w45a", 343, NETTY, true);
    MinecraftVersion V17W45B = MinecraftVersionImpl.of("17w45b", 344, NETTY, true);
    MinecraftVersion V17W46A = MinecraftVersionImpl.of("17w46a", 345, NETTY, true);
    MinecraftVersion V17W47A = MinecraftVersionImpl.of("17w47a", 346, NETTY, true);
    MinecraftVersion V17W47B = MinecraftVersionImpl.of("17w47b", 347, NETTY, true);
    MinecraftVersion V17W48A = MinecraftVersionImpl.of("17w48a", 348, NETTY, true);
    MinecraftVersion V17W49A = MinecraftVersionImpl.of("17w49a", 349, NETTY, true);
    MinecraftVersion V17W49B = MinecraftVersionImpl.of("17w49b", 350, NETTY, true);
    MinecraftVersion V17W50A = MinecraftVersionImpl.of("17w50a", 351, NETTY, true);
    MinecraftVersion V18W01A = MinecraftVersionImpl.of("18w01a", 352, NETTY, true);
    MinecraftVersion V18W02A = MinecraftVersionImpl.of("18w02a", 353, NETTY, true);
    MinecraftVersion V18W03A = MinecraftVersionImpl.of("18w03a", 354, NETTY, true);
    MinecraftVersion V18W03B = MinecraftVersionImpl.of("18w03b", 355, NETTY, true);
    MinecraftVersion V18W05A = MinecraftVersionImpl.of("18w05a", 356, NETTY, true);
    MinecraftVersion V18W06A = MinecraftVersionImpl.of("18w06a", 357, NETTY, true);
    MinecraftVersion V18W07A = MinecraftVersionImpl.of("18w07a", 358, NETTY, true);
    MinecraftVersion V18W07B = MinecraftVersionImpl.of("18w07b", 359, NETTY, true);
    MinecraftVersion V18W07C = MinecraftVersionImpl.of("18w07c", 360, NETTY, true);
    MinecraftVersion V18W08A = MinecraftVersionImpl.of("18w08a", 361, NETTY, true);
    MinecraftVersion V18W08B = MinecraftVersionImpl.of("18w08b", 362, NETTY, true);
    MinecraftVersion V18W09A = MinecraftVersionImpl.of("18w09a", 363, NETTY, true);
    MinecraftVersion V18W10A = MinecraftVersionImpl.of("18w10a", 364, NETTY, true);
    MinecraftVersion V18W10B = MinecraftVersionImpl.of("18w10b", 365, NETTY, true);
    MinecraftVersion V18W10C = MinecraftVersionImpl.of("18w10c", 366, NETTY, true);
    MinecraftVersion V18W10D = MinecraftVersionImpl.of("18w10d", 367, NETTY, true);
    MinecraftVersion V18W11A = MinecraftVersionImpl.of("18w11a", 368, NETTY, true);
    MinecraftVersion V18W14A = MinecraftVersionImpl.of("18w14a", 369, NETTY, true);
    MinecraftVersion V18W14B = MinecraftVersionImpl.of("18w14b", 370, NETTY, true);
    MinecraftVersion V18W15A = MinecraftVersionImpl.of("18w15a", 371, NETTY, true);
    MinecraftVersion V18W16A = MinecraftVersionImpl.of("18w16a", 372, NETTY, true);
    MinecraftVersion V18W19A = MinecraftVersionImpl.of("18w19a", 373, NETTY, true);
    MinecraftVersion V18W19B = MinecraftVersionImpl.of("18w19b", 374, NETTY, true);
    MinecraftVersion V18W20A = MinecraftVersionImpl.of("18w20a", 375, NETTY, true);
    MinecraftVersion V18W20B = MinecraftVersionImpl.of("18w20b", 376, NETTY, true);
    MinecraftVersion V18W20C = MinecraftVersionImpl.of("18w20c", 377, NETTY, true);
    MinecraftVersion V18W21A = MinecraftVersionImpl.of("18w21a", 378, NETTY, true);
    MinecraftVersion V18W21B = MinecraftVersionImpl.of("18w21b", 379, NETTY, true);
    MinecraftVersion V18W22A = MinecraftVersionImpl.of("18w22a", 380, NETTY, true);
    MinecraftVersion V18W22B = MinecraftVersionImpl.of("18w22b", 381, NETTY, true);
    MinecraftVersion V18W22C = MinecraftVersionImpl.of("18w22c", 382, NETTY, true);
    MinecraftVersion V13_PRE1 = MinecraftVersionImpl.of("1.13-pre1", 383, NETTY, true);
    MinecraftVersion V13_PRE3 = MinecraftVersionImpl.of("1.13-pre3", 385, NETTY, true);
    MinecraftVersion V13_PRE2 = MinecraftVersionImpl.of("1.13-pre2", 384, NETTY, true);
    MinecraftVersion V13_PRE4 = MinecraftVersionImpl.of("1.13-pre4", 386, NETTY, true);
    MinecraftVersion V13_PRE5 = MinecraftVersionImpl.of("1.13-pre5", 387, NETTY, true);
    MinecraftVersion V13_PRE6 = MinecraftVersionImpl.of("1.13-pre6", 388, NETTY, true);
    MinecraftVersion V13_PRE7 = MinecraftVersionImpl.of("1.13-pre7", 389, NETTY, true);
    MinecraftVersion V13_PRE8 = MinecraftVersionImpl.of("1.13-pre8", 390, NETTY, true);
    MinecraftVersion V13_PRE9 = MinecraftVersionImpl.of("1.13-pre9", 391, NETTY, true);
    MinecraftVersion V13_PRE10 = MinecraftVersionImpl.of("1.13-pre10", 392, NETTY, true);
    MinecraftVersion V13 = MinecraftVersionImpl.of("1.13", 393, NETTY);
    MinecraftVersion V18W30A = MinecraftVersionImpl.of("18w30a", 394, NETTY, true);
    MinecraftVersion V18W30B = MinecraftVersionImpl.of("18w30b", 395, NETTY, true);
    MinecraftVersion V18W31A = MinecraftVersionImpl.of("18w31a", 396, NETTY, true);
    MinecraftVersion V18W32A = MinecraftVersionImpl.of("18w32a", 397, NETTY, true);
    MinecraftVersion V18W33A = MinecraftVersionImpl.of("18w33a", 398, NETTY, true);
    MinecraftVersion V13_1_PRE1 = MinecraftVersionImpl.of("1.13.1-pre1", 399, NETTY, true);
    MinecraftVersion V13_1_PRE2 = MinecraftVersionImpl.of("1.13.1-pre2", 400, NETTY, true);
    MinecraftVersion V13_1 = MinecraftVersionImpl.of("1.13.1", 401, NETTY);
    MinecraftVersion V13_2_PRE1 = MinecraftVersionImpl.of("1.13.2-pre1", 402, NETTY, true);
    MinecraftVersion V13_2_PRE2 = MinecraftVersionImpl.of("1.13.2-pre2", 403, NETTY, true);
    MinecraftVersion V13_2 = MinecraftVersionImpl.of("1.13.2", 404, NETTY);
    MinecraftVersion V18W43A = MinecraftVersionImpl.of("18w43a", 440, NETTY, true);
    MinecraftVersion V18W43B = MinecraftVersionImpl.of("18w43b", 441, NETTY, true);
    MinecraftVersion V18W43C = MinecraftVersionImpl.of("18w43c", 442, NETTY, true);
    MinecraftVersion V18W44A = MinecraftVersionImpl.of("18w44a", 443, NETTY, true);
    MinecraftVersion V18W45A = MinecraftVersionImpl.of("18w45a", 444, NETTY, true);
    MinecraftVersion V18W46A = MinecraftVersionImpl.of("18w46a", 445, NETTY, true);
    MinecraftVersion V18W47A = MinecraftVersionImpl.of("18w47a", 446, NETTY, true);
    MinecraftVersion V18W47B = MinecraftVersionImpl.of("18w47b", 447, NETTY, true);
    MinecraftVersion V18W48A = MinecraftVersionImpl.of("18w48a", 448, NETTY, true);
    MinecraftVersion V18W48B = MinecraftVersionImpl.of("18w48b", 449, NETTY, true);
    MinecraftVersion V18W49A = MinecraftVersionImpl.of("18w49a", 450, NETTY, true);
    MinecraftVersion V18W50A = MinecraftVersionImpl.of("18w50a", 451, NETTY, true);
    MinecraftVersion V19W02A = MinecraftVersionImpl.of("19w02a", 452, NETTY, true);
    MinecraftVersion V19W03A = MinecraftVersionImpl.of("19w03a", 453, NETTY, true);
    MinecraftVersion V19W03B = MinecraftVersionImpl.of("19w03b", 454, NETTY, true);
    MinecraftVersion V19W03C = MinecraftVersionImpl.of("19w03c", 455, NETTY, true);
    MinecraftVersion V19W04A = MinecraftVersionImpl.of("19w04a", 456, NETTY, true);
    MinecraftVersion V19W04B = MinecraftVersionImpl.of("19w04b", 457, NETTY, true);
    MinecraftVersion V19W05A = MinecraftVersionImpl.of("19w05a", 458, NETTY, true);
    MinecraftVersion V19W06A = MinecraftVersionImpl.of("19w06a", 459, NETTY, true);
    MinecraftVersion V19W07A = MinecraftVersionImpl.of("19w07a", 460, NETTY, true);
    MinecraftVersion V19W08A = MinecraftVersionImpl.of("19w08a", 461, NETTY, true);
    MinecraftVersion V19W08B = MinecraftVersionImpl.of("19w08b", 462, NETTY, true);
    MinecraftVersion V19W09A = MinecraftVersionImpl.of("19w09a", 463, NETTY, true);
    MinecraftVersion V19W11A = MinecraftVersionImpl.of("19w11a", 464, NETTY, true);
    MinecraftVersion V19W11B = MinecraftVersionImpl.of("19w11b", 465, NETTY, true);
    MinecraftVersion V19W12A = MinecraftVersionImpl.of("19w12a", 466, NETTY, true);
    MinecraftVersion V19W12B = MinecraftVersionImpl.of("19w12b", 467, NETTY, true);
    MinecraftVersion V19W13A = MinecraftVersionImpl.of("19w13a", 468, NETTY, true);
    MinecraftVersion V19W13B = MinecraftVersionImpl.of("19w13b", 469, NETTY, true);
    MinecraftVersion V3D_SHAREWARE =
            MinecraftVersionImpl.of("3D Shareware v1.34", 1, APRIL_FOOLS, true);
    MinecraftVersion V19W14A = MinecraftVersionImpl.of("19w14a", 470, NETTY, true);
    MinecraftVersion V19W14B = MinecraftVersionImpl.of("19w14b", 471, NETTY, true);
    MinecraftVersion V14_PRE1 = MinecraftVersionImpl.of("1.14 Pre-Release 1", 472, NETTY, true);
    MinecraftVersion V14_PRE2 = MinecraftVersionImpl.of("1.14 Pre-Release 2", 473, NETTY, true);
    MinecraftVersion V14_PRE3 = MinecraftVersionImpl.of("1.14 Pre-Release 3", 474, NETTY, true);
    MinecraftVersion V14_PRE4 = MinecraftVersionImpl.of("1.14 Pre-Release 4", 475, NETTY, true);
    MinecraftVersion V14_PRE5 = MinecraftVersionImpl.of("1.14 Pre-Release 5", 476, NETTY, true);
    MinecraftVersion V14 = MinecraftVersionImpl.of("1.14", 477, NETTY);
    MinecraftVersion V14_1_PRE1 = MinecraftVersionImpl.of("1.14.1 Pre-Release 1", 478, NETTY, true);
    MinecraftVersion V14_1_PRE2 = MinecraftVersionImpl.of("1.14.1 Pre-Release 2", 479, NETTY, true);
    MinecraftVersion V14_1 = MinecraftVersionImpl.of("1.14.1", 480, NETTY);
    MinecraftVersion V14_2_PRE1 = MinecraftVersionImpl.of("1.14.2 Pre-Release 1", 481, NETTY, true);
    MinecraftVersion V14_2_PRE2 = MinecraftVersionImpl.of("1.14.2 Pre-Release 2", 482, NETTY, true);
    MinecraftVersion V14_2_PRE3 = MinecraftVersionImpl.of("1.14.2 Pre-Release 3", 483, NETTY, true);
    MinecraftVersion V14_2_PRE4 = MinecraftVersionImpl.of("1.14.2 Pre-Release 4", 484, NETTY, true);
    MinecraftVersion V14_2 = MinecraftVersionImpl.of("1.14.2", 485, NETTY);
    MinecraftVersion V14_3_PRE1 = MinecraftVersionImpl.of("1.14.3 Pre-Release 1", 486, NETTY, true);
    MinecraftVersion V14_3_PRE2 = MinecraftVersionImpl.of("1.14.3 Pre-Release 2", 487, NETTY, true);
    MinecraftVersion V14_3_PRE3 = MinecraftVersionImpl.of("1.14.3 Pre-Release 3", 488, NETTY, true);
    MinecraftVersion V14_3_PRE4 = MinecraftVersionImpl.of("1.14.3 Pre-Release 4", 489, NETTY, true);
    MinecraftVersion V14_3 = MinecraftVersionImpl.of("1.14.3", 490, NETTY);
    MinecraftVersion V14_4_PRE1 = MinecraftVersionImpl.of("1.14.4 Pre-Release 1", 491, NETTY, true);
    MinecraftVersion V14_4_PRE2 = MinecraftVersionImpl.of("1.14.4 Pre-Release 2", 492, NETTY, true);
    MinecraftVersion V14_4_PRE3 = MinecraftVersionImpl.of("1.14.4 Pre-Release 3", 493, NETTY, true);
    MinecraftVersion V14_4_PRE4 = MinecraftVersionImpl.of("1.14.4 Pre-Release 4", 494, NETTY, true);
    MinecraftVersion V14_4_PRE5 = MinecraftVersionImpl.of("1.14.4 Pre-Release 5", 495, NETTY, true);
    MinecraftVersion V14_4_PRE6 = MinecraftVersionImpl.of("1.14.4 Pre-Release 6", 496, NETTY, true);
    MinecraftVersion V14_4_PRE7 = MinecraftVersionImpl.of("1.14.4 Pre-Release 7", 497, NETTY, true);
    MinecraftVersion V14_4 = MinecraftVersionImpl.of("1.14.4", 498, NETTY);
    MinecraftVersion V14_3_COMBAT_TEST =
            MinecraftVersionImpl.of("1.14.3 - Combat Test", 500, NETTY, true);
    MinecraftVersion VCOMBAT_TEST_2 = MinecraftVersionImpl.of("Combat Test 2", 501, NETTY, true);
    MinecraftVersion VCOMBAT_TEST_3 = MinecraftVersionImpl.of("Combat Test 3", 502, NETTY, true);
    MinecraftVersion V19W34A = MinecraftVersionImpl.of("19w34a", 550, NETTY, true);
    MinecraftVersion V19W35A = MinecraftVersionImpl.of("19w35a", 551, NETTY, true);
    MinecraftVersion V19W36A = MinecraftVersionImpl.of("19w36a", 552, NETTY, true);
    MinecraftVersion V19W37A = MinecraftVersionImpl.of("19w37a", 553, NETTY, true);
    MinecraftVersion V19W38A = MinecraftVersionImpl.of("19w38a", 554, NETTY, true);
    MinecraftVersion V19W38B = MinecraftVersionImpl.of("19w38b", 555, NETTY, true);
    MinecraftVersion V19W39A = MinecraftVersionImpl.of("19w39a", 556, NETTY, true);
    MinecraftVersion V19W40A = MinecraftVersionImpl.of("19w40a", 557, NETTY, true);
    MinecraftVersion V19W41A = MinecraftVersionImpl.of("19w41a", 558, NETTY, true);
    MinecraftVersion V19W42A = MinecraftVersionImpl.of("19w42a", 559, NETTY, true);
    MinecraftVersion V19W44A = MinecraftVersionImpl.of("19w44a", 560, NETTY, true);
    MinecraftVersion V19W45A = MinecraftVersionImpl.of("19w45a", 561, NETTY, true);
    MinecraftVersion V19W45B = MinecraftVersionImpl.of("19w45b", 562, NETTY, true);
    MinecraftVersion V19W46A = MinecraftVersionImpl.of("19w46a", 563, NETTY, true);
    MinecraftVersion V19W46B = MinecraftVersionImpl.of("19w46b", 564, NETTY, true);
    MinecraftVersion V15_PRE1 = MinecraftVersionImpl.of("1.15 Pre-release 1", 565, NETTY, true);
    MinecraftVersion V15_PRE2 = MinecraftVersionImpl.of("1.15 Pre-Release 2", 566, NETTY, true);
    MinecraftVersion V15_PRE3 = MinecraftVersionImpl.of("1.15 Pre-release 3", 567, NETTY, true);
    MinecraftVersion V15_PRE4 = MinecraftVersionImpl.of("1.15 Pre-release 4", 569, NETTY, true);
    MinecraftVersion V15_PRE5 = MinecraftVersionImpl.of("1.15 Pre-release 5", 570, NETTY, true);
    MinecraftVersion V15_PRE6 = MinecraftVersionImpl.of("1.15 Pre-release 6", 571, NETTY, true);
    MinecraftVersion V15_PRE7 = MinecraftVersionImpl.of("1.15 Pre-release 7", 572, NETTY, true);
    MinecraftVersion V15 = MinecraftVersionImpl.of("1.15", 573, NETTY);
    MinecraftVersion V15_1_PRE1 = MinecraftVersionImpl.of("1.15.1 Pre-release 1", 574, NETTY, true);
    MinecraftVersion V15_1 = MinecraftVersionImpl.of("1.15.1", 575, NETTY);
    MinecraftVersion V15_2_PRE1 = MinecraftVersionImpl.of("1.15.2 Pre-Release 1", 576, NETTY, true);
    MinecraftVersion V15_2_PRE2 = MinecraftVersionImpl.of("1.15.2 Pre-release 2", 577, NETTY, true);
    MinecraftVersion V15_2 = MinecraftVersionImpl.of("1.15.2", 578, NETTY);
    MinecraftVersion VCOMBAT_TEST_4 = MinecraftVersionImpl.of("Combat Test 4", 600, NETTY, true);
    MinecraftVersion VCOMBAT_TEST_5 = MinecraftVersionImpl.of("Combat Test 5", 601, NETTY, true);
    MinecraftVersion V20W06A = MinecraftVersionImpl.of("20w06a", 701, NETTY, true);
    MinecraftVersion V20W07A = MinecraftVersionImpl.of("20w07a", 702, NETTY, true);
    MinecraftVersion V20W08A = MinecraftVersionImpl.of("20w08a", 703, NETTY, true);
    MinecraftVersion V20W09A = MinecraftVersionImpl.of("20w09a", 704, NETTY, true);
    MinecraftVersion V20W10A = MinecraftVersionImpl.of("20w10a", 705, NETTY, true);
    MinecraftVersion V20W11A = MinecraftVersionImpl.of("20w11a", 706, NETTY, true);
    MinecraftVersion V20W12A = MinecraftVersionImpl.of("20w12a", 707, NETTY, true);
    MinecraftVersion V20W13A = MinecraftVersionImpl.of("20w13a", 708, NETTY, true);
    MinecraftVersion V20W13B = MinecraftVersionImpl.of("20w13b", 709, NETTY, true);
    MinecraftVersion V20W14_INFINITE = MinecraftVersionImpl.of("20w14∞", 709, APRIL_FOOLS, true);
    MinecraftVersion V20W14A = MinecraftVersionImpl.of("20w14a", 710, NETTY, true);
    MinecraftVersion V20W15A = MinecraftVersionImpl.of("20w15a", 711, NETTY, true);
    MinecraftVersion V20W16A = MinecraftVersionImpl.of("20w16a", 712, NETTY, true);
    MinecraftVersion V20W17A = MinecraftVersionImpl.of("20w17a", 713, NETTY, true);
    MinecraftVersion V20W18A = MinecraftVersionImpl.of("20w18a", 714, NETTY, true);
    MinecraftVersion V20W19A = MinecraftVersionImpl.of("20w19a", 715, NETTY, true);
    MinecraftVersion V20W20A = MinecraftVersionImpl.of("20w20a", 716, NETTY, true);
    MinecraftVersion V20W20B = MinecraftVersionImpl.of("20w20b", 717, NETTY, true);
    MinecraftVersion V20W21A = MinecraftVersionImpl.of("20w21a", 718, NETTY, true);
    MinecraftVersion V20W22A = MinecraftVersionImpl.of("20w22a", 719, NETTY, true);
    MinecraftVersion V16_PRE1 = MinecraftVersionImpl.of("1.16 Pre-release 1", 721, NETTY, true);
    MinecraftVersion V16_PRE2 = MinecraftVersionImpl.of("1.16 Pre-release 2", 722, NETTY, true);
    MinecraftVersion V16_PRE3 = MinecraftVersionImpl.of("1.16 Pre-release 3", 725, NETTY, true);
    MinecraftVersion V16_PRE4 = MinecraftVersionImpl.of("1.16 Pre-release 4", 727, NETTY, true);
    MinecraftVersion V16_PRE5 = MinecraftVersionImpl.of("1.16 Pre-release 5", 729, NETTY, true);
    MinecraftVersion V16_PRE6 = MinecraftVersionImpl.of("1.16 Pre-release 6", 730, NETTY, true);
    MinecraftVersion V16_PRE7 = MinecraftVersionImpl.of("1.16 Pre-release 7", 732, NETTY, true);
    MinecraftVersion V16_PRE8 = MinecraftVersionImpl.of("1.16 Pre-release 8", 733, NETTY, true);
    MinecraftVersion V16_RC1 =
            MinecraftVersionImpl.of("1.16 Release Candidate 1", 734, NETTY, true);
    MinecraftVersion V16 = MinecraftVersionImpl.of("1.16", 735, NETTY);
    MinecraftVersion V16_1 = MinecraftVersionImpl.of("1.16.1", 736, NETTY);
    MinecraftVersion V20W27A = MinecraftVersionImpl.of("20w27a", 738, NETTY, true);
    MinecraftVersion V20W28A = MinecraftVersionImpl.of("20w28a", 740, NETTY, true);
    MinecraftVersion V20W29A = MinecraftVersionImpl.of("20w29a", 741, NETTY, true);
    MinecraftVersion V20W30A = MinecraftVersionImpl.of("20w30a", 742, NETTY, true);
    MinecraftVersion V16_2_PRE1 = MinecraftVersionImpl.of("1.16.2 Pre-release 1", 744, NETTY, true);
    MinecraftVersion V16_2_PRE2 = MinecraftVersionImpl.of("1.16.2 Pre-release 2", 746, NETTY, true);
    MinecraftVersion V16_2_PRE3 = MinecraftVersionImpl.of("1.16.2 Pre-release 3", 748, NETTY, true);
    MinecraftVersion V16_2_RC1 =
            MinecraftVersionImpl.of("1.16.2 Release Candidate 1", 749, NETTY, true);
    MinecraftVersion V16_2_RC2 =
            MinecraftVersionImpl.of("1.16.2 Release Candidate 2", 750, NETTY, true);
    MinecraftVersion V16_2 = MinecraftVersionImpl.of("1.16.2", 751, NETTY);
    MinecraftVersion V16_3_RC1 =
            MinecraftVersionImpl.of("1.16.3 Release Candidate 1", 752, NETTY, true);
    MinecraftVersion V16_3 = MinecraftVersionImpl.of("1.16.3", 753, NETTY);
    MinecraftVersion V16_4_PRE1 =
            MinecraftVersionImpl.of("1.16.4 Pre-release 1", 1073741825, NETTY, true);
    MinecraftVersion V16_4_PRE2 =
            MinecraftVersionImpl.of("1.16.4 Pre-release 2", 1073741826, NETTY, true);
    MinecraftVersion V16_4_RC1 =
            MinecraftVersionImpl.of("1.16.4 Release Candidate 1", 1073741827, NETTY, true);
    MinecraftVersion V16_4 = MinecraftVersionImpl.of("1.16.4", 754, NETTY);
    MinecraftVersion V16_5_RC1 =
            MinecraftVersionImpl.of("1.16.5 Release Candidate 1", 1073741834, NETTY, true);
    MinecraftVersion V16_5 = MinecraftVersionImpl.of("1.16.5", 754, NETTY);
    MinecraftVersion VCOMBAT_TEST_6 = MinecraftVersionImpl.of("Combat Test 6", 801, NETTY, true);
    MinecraftVersion VCOMBAT_TEST_7 = MinecraftVersionImpl.of("Combat Test 7", 802, NETTY, true);
    MinecraftVersion VCOMBAT_TEST_7B = MinecraftVersionImpl.of("Combat Test 7b", 802, NETTY, true);
    MinecraftVersion VCOMBAT_TEST_7C = MinecraftVersionImpl.of("Combat Test 7c", 802, NETTY, true);
    MinecraftVersion VCOMBAT_TEST_8 = MinecraftVersionImpl.of("Combat Test 8", 803, NETTY, true);
    MinecraftVersion VCOMBAT_TEST_8B = MinecraftVersionImpl.of("Combat Test 8b", 803, NETTY, true);
    MinecraftVersion VCOMBAT_TEST_8C = MinecraftVersionImpl.of("Combat Test 8c", 803, NETTY, true);
    MinecraftVersion V20W45A = MinecraftVersionImpl.of("20w45a", 1073741829, NETTY, true);
    MinecraftVersion V20W46A = MinecraftVersionImpl.of("20w46a", 1073741830, NETTY, true);
    MinecraftVersion V20W48A = MinecraftVersionImpl.of("20w48a", 1073741831, NETTY, true);
    MinecraftVersion V20W49A = MinecraftVersionImpl.of("20w49a", 1073741832, NETTY, true);
    MinecraftVersion V20W51A = MinecraftVersionImpl.of("20w51a", 1073741833, NETTY, true);
    MinecraftVersion V21W03A = MinecraftVersionImpl.of("21w03a", 1073741835, NETTY, true);
    MinecraftVersion V21W05A = MinecraftVersionImpl.of("21w05a", 1073741836, NETTY, true);
    MinecraftVersion V21W05B = MinecraftVersionImpl.of("21w05b", 1073741837, NETTY, true);
    MinecraftVersion V21W06A = MinecraftVersionImpl.of("21w06a", 1073741838, NETTY, true);
    MinecraftVersion V21W07A = MinecraftVersionImpl.of("21w07a", 1073741839, NETTY, true);
    MinecraftVersion V21W08A = MinecraftVersionImpl.of("21w08a", 1073741840, NETTY, true);
    MinecraftVersion V21W08B = MinecraftVersionImpl.of("21w08b", 1073741841, NETTY, true);
    MinecraftVersion V21W10A = MinecraftVersionImpl.of("21w10a", 1073741842, NETTY, true);
    MinecraftVersion V21W11A = MinecraftVersionImpl.of("21w11a", 1073741843, NETTY, true);
    MinecraftVersion V21W13A = MinecraftVersionImpl.of("21w13a", 1073741844, NETTY, true);
    MinecraftVersion V21W14A = MinecraftVersionImpl.of("21w14a", 1073741845, NETTY, true);
    MinecraftVersion V21W15A = MinecraftVersionImpl.of("21w15a", 1073741846, NETTY, true);
    MinecraftVersion V21W16A = MinecraftVersionImpl.of("21w16a", 1073741847, NETTY, true);
    MinecraftVersion V21W17A = MinecraftVersionImpl.of("21w17a", 1073741849, NETTY, true);
    MinecraftVersion V21W18A = MinecraftVersionImpl.of("21w18a", 1073741850, NETTY, true);
    MinecraftVersion V21W19A = MinecraftVersionImpl.of("21w19a", 1073741851, NETTY, true);
    MinecraftVersion V21W20A = MinecraftVersionImpl.of("21w20a", 1073741852, NETTY, true);
    MinecraftVersion V17_PRE1 =
            MinecraftVersionImpl.of("1.17 Pre-release 1", 1073741853, NETTY, true);
    MinecraftVersion V17_PRE2 =
            MinecraftVersionImpl.of("1.17 Pre-release 2", 1073741854, NETTY, true);
    MinecraftVersion V17_PRE3 =
            MinecraftVersionImpl.of("1.17 Pre-release 3", 1073741855, NETTY, true);
    MinecraftVersion V17_PRE4 =
            MinecraftVersionImpl.of("1.17 Pre-release 4", 1073741856, NETTY, true);
    MinecraftVersion V17_PRE5 =
            MinecraftVersionImpl.of("1.17 Pre-release 5", 1073741857, NETTY, true);
    MinecraftVersion V17_RC1 =
            MinecraftVersionImpl.of("1.17 Release Candidate 1", 1073741858, NETTY, true);
    MinecraftVersion V17_RC2 =
            MinecraftVersionImpl.of("1.17 Release Candidate 2", 1073741859, NETTY, true);
    MinecraftVersion V17 = MinecraftVersionImpl.of("1.17", 755, NETTY);
    MinecraftVersion V17_1_PRE1 =
            MinecraftVersionImpl.of("1.17.1 Pre-release 1", 1073741860, NETTY, true);
    MinecraftVersion V17_1_PRE2 =
            MinecraftVersionImpl.of("1.17.1 Pre-release 2", 1073741861, NETTY, true);
    MinecraftVersion V17_1_PRE3 =
            MinecraftVersionImpl.of("1.17.1 Pre-release 3", 1073741862, NETTY, true);
    MinecraftVersion V17_1_RC1 =
            MinecraftVersionImpl.of("1.17.1 Release Candidate 1", 1073741863, NETTY, true);
    MinecraftVersion V17_1_RC2 =
            MinecraftVersionImpl.of("1.17.1 Release Candidate 2", 1073741864, NETTY, true);
    MinecraftVersion V17_1 = MinecraftVersionImpl.of("1.17.1", 756, NETTY);
    MinecraftVersion V21W37A = MinecraftVersionImpl.of("21w37a", 1073741865, NETTY, true);
    MinecraftVersion V21W38A = MinecraftVersionImpl.of("21w38a", 1073741866, NETTY, true);
    MinecraftVersion V21W39A = MinecraftVersionImpl.of("21w39a", 1073741867, NETTY, true);
    MinecraftVersion V21W40A = MinecraftVersionImpl.of("21w40a", 1073741868, NETTY, true);
    MinecraftVersion V21W41A = MinecraftVersionImpl.of("21w41a", 1073741869, NETTY, true);
    MinecraftVersion V21W42A = MinecraftVersionImpl.of("21w42a", 1073741870, NETTY, true);
    MinecraftVersion V21W43A = MinecraftVersionImpl.of("21w43a", 1073741871, NETTY, true);
    MinecraftVersion V21W44A = MinecraftVersionImpl.of("21w44a", 1073741872, NETTY, true);
    MinecraftVersion V18_PRE1 =
            MinecraftVersionImpl.of("1.18 Pre-release 1", 1073741873, NETTY, true);
    MinecraftVersion V18_PRE2 =
            MinecraftVersionImpl.of("1.18 Pre-release 2", 1073741874, NETTY, true);
    MinecraftVersion V18_PRE3 =
            MinecraftVersionImpl.of("1.18 Pre-release 3", 1073741875, NETTY, true);
    MinecraftVersion V18_PRE4 =
            MinecraftVersionImpl.of("1.18 Pre-release 4", 1073741876, NETTY, true);
    MinecraftVersion V18_PRE5 =
            MinecraftVersionImpl.of("1.18 Pre-release 5", 1073741877, NETTY, true);
    MinecraftVersion V18_PRE6 =
            MinecraftVersionImpl.of("1.18 Pre-release 6", 1073741878, NETTY, true);
    MinecraftVersion V18_PRE7 =
            MinecraftVersionImpl.of("1.18 Pre-release 7", 1073741879, NETTY, true);
    MinecraftVersion V18_PRE8 =
            MinecraftVersionImpl.of("1.18 Pre-release 8", 1073741880, NETTY, true);
    MinecraftVersion V18_RC1 =
            MinecraftVersionImpl.of("1.18 Release Candidate 1", 1073741881, NETTY, true);
    MinecraftVersion V18_RC2 =
            MinecraftVersionImpl.of("1.18 Release Candidate 2", 1073741882, NETTY, true);
    MinecraftVersion V18_RC3 =
            MinecraftVersionImpl.of("1.18 Release Candidate 3", 1073741883, NETTY, true);
    MinecraftVersion V18_RC4 =
            MinecraftVersionImpl.of("1.18 Release Candidate 4", 1073741884, NETTY, true);
    MinecraftVersion V18 = MinecraftVersionImpl.of("1.18", 757, NETTY);
    MinecraftVersion V18_1_PRE1 =
            MinecraftVersionImpl.of("1.18.1 Pre-release 1", 1073741885, NETTY, true);
    MinecraftVersion V18_1_RC1 =
            MinecraftVersionImpl.of("1.18.1 Release Candidate 1", 1073741886, NETTY, true);
    MinecraftVersion V18_1_RC2 =
            MinecraftVersionImpl.of("1.18.1 Release Candidate 2", 1073741887, NETTY, true);
    MinecraftVersion V18_1_RC3 =
            MinecraftVersionImpl.of("1.18.1 Release Candidate 3", 1073741888, NETTY, true);
    MinecraftVersion V18_1 = MinecraftVersionImpl.of("1.18.1", 757, NETTY);
    MinecraftVersion V22W03A = MinecraftVersionImpl.of("22w03a", 1073741889, NETTY, true);
    MinecraftVersion V22W05A = MinecraftVersionImpl.of("22w05a", 1073741890, NETTY, true);
    MinecraftVersion V22W06A = MinecraftVersionImpl.of("22w06a", 1073741891, NETTY, true);
    MinecraftVersion V22W07A = MinecraftVersionImpl.of("22w07a", 1073741892, NETTY, true);
    MinecraftVersion V18_2_PRE1 =
            MinecraftVersionImpl.of("1.18.2 Pre-release 1", 1073741894, NETTY, true);
    MinecraftVersion V18_2_PRE2 =
            MinecraftVersionImpl.of("1.18.2 Pre-release 2", 1073741895, NETTY, true);
    MinecraftVersion V18_2_PRE3 =
            MinecraftVersionImpl.of("1.18.2 Pre-release 3", 1073741896, NETTY, true);
    MinecraftVersion V18_2_RC1 =
            MinecraftVersionImpl.of("1.18.2 Release Candidate 1", 1073741897, NETTY, true);
    MinecraftVersion V18_2 = MinecraftVersionImpl.of("1.18.2", 758, NETTY);
    MinecraftVersion VDEEP_DARK_EXPER =
            MinecraftVersionImpl.of("Deep Dark Experimental Snapshot 1", 1073741893, NETTY, true);
    MinecraftVersion V22W11A = MinecraftVersionImpl.of("22w11a", 1073741898, NETTY, true);
    MinecraftVersion V22W12A = MinecraftVersionImpl.of("22w12a", 1073741899, NETTY, true);
    MinecraftVersion V22W13A = MinecraftVersionImpl.of("22w13a", 1073741900, NETTY, true);
    MinecraftVersion V22W13_ONE_BLOCK_AT_A_TIME =
            MinecraftVersionImpl.of("22w13oneBlockAtATime", 1073741901, APRIL_FOOLS, true);
    MinecraftVersion V22W14A = MinecraftVersionImpl.of("22w14a", 1073741902, NETTY, true);
    MinecraftVersion V22W15A = MinecraftVersionImpl.of("22w15a", 1073741903, NETTY, true);
    MinecraftVersion V22W16A = MinecraftVersionImpl.of("22w16a", 1073741904, NETTY, true);
    MinecraftVersion V22W16B = MinecraftVersionImpl.of("22w16b", 1073741905, NETTY, true);
    MinecraftVersion V22W17A = MinecraftVersionImpl.of("22w17a", 1073741906, NETTY, true);
    MinecraftVersion V22W18A = MinecraftVersionImpl.of("22w18a", 1073741907, NETTY, true);
    MinecraftVersion V22W19A = MinecraftVersionImpl.of("22w19a", 1073741908, NETTY, true);
    MinecraftVersion V19_PRE1 =
            MinecraftVersionImpl.of("1.19 Pre-release 1", 1073741909, NETTY, true);
    MinecraftVersion V19_PRE2 =
            MinecraftVersionImpl.of("1.19 Pre-release 2", 1073741910, NETTY, true);
    MinecraftVersion V19_PRE3 =
            MinecraftVersionImpl.of("1.19 Pre-release 3", 1073741911, NETTY, true);
    MinecraftVersion V19_PRE4 =
            MinecraftVersionImpl.of("1.19 Pre-release 4", 1073741912, NETTY, true);
    MinecraftVersion V19_PRE5 =
            MinecraftVersionImpl.of("1.19 Pre-release 5", 1073741913, NETTY, true);
    MinecraftVersion V19_RC1 =
            MinecraftVersionImpl.of("1.19 Release Candidate 1", 1073741914, NETTY, true);
    MinecraftVersion V19_RC2 =
            MinecraftVersionImpl.of("1.19 Release Candidate 2", 1073741915, NETTY, true);
    MinecraftVersion V19 = MinecraftVersionImpl.of("1.19", 759, NETTY);
    MinecraftVersion V22W24A = MinecraftVersionImpl.of("22w24a", 1073741916, NETTY, true);
    MinecraftVersion V19_1_PRE1 =
            MinecraftVersionImpl.of("1.19.1 Pre-release 1", 1073741917, NETTY, true);
    MinecraftVersion V19_1_RC1 =
            MinecraftVersionImpl.of("1.19.1 Release Candidate 1", 1073741918, NETTY, true);
    MinecraftVersion V19_1_PRE2 =
            MinecraftVersionImpl.of("1.19.1 Pre-release 2", 1073741919, NETTY, true);
    MinecraftVersion V19_1_PRE3 =
            MinecraftVersionImpl.of("1.19.1 Pre-release 3", 1073741920, NETTY, true);
    MinecraftVersion V19_1_PRE4 =
            MinecraftVersionImpl.of("1.19.1 Pre-release 4", 1073741921, NETTY, true);
    MinecraftVersion V19_1_PRE5 =
            MinecraftVersionImpl.of("1.19.1 Pre-release 5", 1073741922, NETTY, true);
    MinecraftVersion V19_1_PRE6 =
            MinecraftVersionImpl.of("1.19.1 Pre-release 6", 1073741923, NETTY, true);
    MinecraftVersion V19_1_RC2 =
            MinecraftVersionImpl.of("1.19.1 Release Candidate 2", 1073741924, NETTY, true);
    MinecraftVersion V19_1_RC3 =
            MinecraftVersionImpl.of("1.19.1 Release Candidate 3", 1073741925, NETTY, true);
    MinecraftVersion V19_1 = MinecraftVersionImpl.of("1.19.1", 760, NETTY);
    MinecraftVersion V19_2_RC1 =
            MinecraftVersionImpl.of("1.19.2 Release Candidate 1", 1073741926, NETTY, true);
    MinecraftVersion V19_2_RC2 =
            MinecraftVersionImpl.of("1.19.2 Release Candidate 2", 1073741927, NETTY, true);
    MinecraftVersion V19_2 = MinecraftVersionImpl.of("1.19.2", 760, NETTY);
    MinecraftVersion V22W42A = MinecraftVersionImpl.of("22w42a", 1073741928, NETTY, true);
    MinecraftVersion V22W43A = MinecraftVersionImpl.of("22w43a", 1073741929, NETTY, true);
    MinecraftVersion V22W44A = MinecraftVersionImpl.of("22w44a", 1073741930, NETTY, true);
    MinecraftVersion V22W45A = MinecraftVersionImpl.of("22w45a", 1073741931, NETTY, true);
    MinecraftVersion V22W46A = MinecraftVersionImpl.of("22w46a", 1073741932, NETTY, true);
    MinecraftVersion V19_3_PRE1 =
            MinecraftVersionImpl.of("1.19.3 Pre-release 1", 1073741933, NETTY, true);
    MinecraftVersion V19_3_PRE2 =
            MinecraftVersionImpl.of("1.19.3 Pre-release 2", 1073741934, NETTY, true);
    MinecraftVersion V19_3_PRE3 =
            MinecraftVersionImpl.of("1.19.3 Pre-release 3", 1073741935, NETTY, true);
    MinecraftVersion V19_3_RC1 =
            MinecraftVersionImpl.of("1.19.3 Release Candidate 1", 1073741936, NETTY, true);
    MinecraftVersion V19_3_RC2 =
            MinecraftVersionImpl.of("1.19.3 Release Candidate 2", 1073741937, NETTY, true);
    MinecraftVersion V19_3_RC3 =
            MinecraftVersionImpl.of("1.19.3 Release Candidate 3", 1073741938, NETTY, true);
    MinecraftVersion V19_3 = MinecraftVersionImpl.of("1.19.3", 761, NETTY);
    MinecraftVersion V23W03A = MinecraftVersionImpl.of("23w03a", 1073741939, NETTY, true);
    MinecraftVersion V23W04A = MinecraftVersionImpl.of("23w04a", 1073741940, NETTY, true);
    MinecraftVersion V23W05A = MinecraftVersionImpl.of("23w05a", 1073741941, NETTY, true);
    MinecraftVersion V23W06A = MinecraftVersionImpl.of("23w06a", 1073741942, NETTY, true);
    MinecraftVersion V23W07A = MinecraftVersionImpl.of("23w07a", 1073741943, NETTY, true);
    MinecraftVersion V19_4_PRE1 =
            MinecraftVersionImpl.of("1.19.4 Pre-release 1", 1073741944, NETTY, true);
    MinecraftVersion V19_4_PRE2 =
            MinecraftVersionImpl.of("1.19.4 Pre-release 2", 1073741945, NETTY, true);
    MinecraftVersion V19_4_PRE3 =
            MinecraftVersionImpl.of("1.19.4 Pre-release 3", 1073741946, NETTY, true);
    MinecraftVersion V19_4_PRE4 =
            MinecraftVersionImpl.of("1.19.4 Pre-release 4", 1073741947, NETTY, true);
    MinecraftVersion V19_4_RC1 =
            MinecraftVersionImpl.of("1.19.4 Release Candidate 1", 1073741948, NETTY, true);
    MinecraftVersion V19_4_RC2 =
            MinecraftVersionImpl.of("1.19.4 Release Candidate 2", 1073741949, NETTY, true);
    MinecraftVersion V19_4_RC3 =
            MinecraftVersionImpl.of("1.19.4 Release Candidate 3", 1073741950, NETTY, true);
    MinecraftVersion V19_4 = MinecraftVersionImpl.of("1.19.4", 762, NETTY);
    MinecraftVersion V23W12A = MinecraftVersionImpl.of("23w12a", 1073741951, NETTY, true);
    MinecraftVersion V23W13A = MinecraftVersionImpl.of("23w13a", 1073741952, NETTY, true);
    MinecraftVersion V23W13A_OR_B =
            MinecraftVersionImpl.of("23w13a_or_b", 1073741953, APRIL_FOOLS, true);
    MinecraftVersion V23W14A = MinecraftVersionImpl.of("23w14a", 1073741954, NETTY, true);
    MinecraftVersion V23W16A = MinecraftVersionImpl.of("23w16a", 1073741955, NETTY, true);
    MinecraftVersion V23W17A = MinecraftVersionImpl.of("23w17a", 1073741956, NETTY, true);
    MinecraftVersion V23W18A = MinecraftVersionImpl.of("23w18a", 1073741957, NETTY, true);
    MinecraftVersion V20_PRE1 =
            MinecraftVersionImpl.of("1.20 Pre-release 1", 1073741958, NETTY, true);
    MinecraftVersion V20_PRE2 =
            MinecraftVersionImpl.of("1.20 Pre-release 2", 1073741959, NETTY, true);
    MinecraftVersion V20_PRE3 =
            MinecraftVersionImpl.of("1.20 Pre-release 3", 1073741960, NETTY, true);
    MinecraftVersion V20_PRE4 =
            MinecraftVersionImpl.of("1.20 Pre-release 4", 1073741961, NETTY, true);
    MinecraftVersion V20_PRE5 =
            MinecraftVersionImpl.of("1.20 Pre-release 5", 1073741962, NETTY, true);
    MinecraftVersion V20_PRE6 =
            MinecraftVersionImpl.of("1.20 Pre-release 6", 1073741963, NETTY, true);
    MinecraftVersion V20_PRE7 =
            MinecraftVersionImpl.of("1.20 Pre-release 7", 1073741964, NETTY, true);
    MinecraftVersion V20_RC1 =
            MinecraftVersionImpl.of("1.20 Release Candidate 1", 1073741965, NETTY, true);
    MinecraftVersion V20 = MinecraftVersionImpl.of("1.20", 763, NETTY);
    MinecraftVersion V20_1_RC1 =
            MinecraftVersionImpl.of("1.20.1 Release Candidate 1", 1073741966, NETTY, true);
    MinecraftVersion V20_1 = MinecraftVersionImpl.of("1.20.1", 763, NETTY);
    MinecraftVersion V23W31A = MinecraftVersionImpl.of("23w31a", 1073741968, NETTY, true);
    MinecraftVersion V23W32A = MinecraftVersionImpl.of("23w32a", 1073741969, NETTY, true);
    MinecraftVersion V23W33A = MinecraftVersionImpl.of("23w33a", 1073741970, NETTY, true);
    MinecraftVersion V23W35A = MinecraftVersionImpl.of("23w35a", 1073741971, NETTY, true);
    MinecraftVersion V20_2_PRE1 =
            MinecraftVersionImpl.of("1.20.2 Pre-release 1", 1073741972, NETTY, true);
    MinecraftVersion V20_2_PRE2 =
            MinecraftVersionImpl.of("1.20.2 Pre-release 2", 1073741973, NETTY, true);
    MinecraftVersion V20_2_PRE3 =
            MinecraftVersionImpl.of("1.20.2 Pre-Release 3", 1073741974, NETTY, true);
    MinecraftVersion V20_2_PRE4 =
            MinecraftVersionImpl.of("1.20.2 Pre-Release 4", 1073741975, NETTY, true);
    MinecraftVersion V20_2_RC1 =
            MinecraftVersionImpl.of("1.20.2 Release Candidate 1", 1073741976, NETTY, true);
    MinecraftVersion V20_2_RC2 =
            MinecraftVersionImpl.of("1.20.2 Release Candidate 2", 1073741977, NETTY, true);
    MinecraftVersion V20_2 = MinecraftVersionImpl.of("1.20.2", 764, NETTY);
    MinecraftVersion V23W40A = MinecraftVersionImpl.of("23w40a", 1073741978, NETTY, true);
    MinecraftVersion V23W41A = MinecraftVersionImpl.of("23w41a", 1073741980, NETTY, true);
    MinecraftVersion V23W42A = MinecraftVersionImpl.of("23w42a", 1073741981, NETTY, true);
    MinecraftVersion V23W43A = MinecraftVersionImpl.of("23w43a", 1073741983, NETTY, true);
    MinecraftVersion V23W43B = MinecraftVersionImpl.of("23w43b", 1073741984, NETTY, true);
    MinecraftVersion V23W44A = MinecraftVersionImpl.of("23w44a", 1073741985, NETTY, true);
    MinecraftVersion V23W45A = MinecraftVersionImpl.of("23w45a", 1073741986, NETTY, true);
    MinecraftVersion V23W46A = MinecraftVersionImpl.of("23w46a", 1073741987, NETTY, true);
    MinecraftVersion V20_3_PRE1 =
            MinecraftVersionImpl.of("1.20.3 Pre-Release 1", 1073741988, NETTY, true);
    MinecraftVersion V20_3_PRE2 =
            MinecraftVersionImpl.of("1.20.3 Pre-Release 2", 1073741989, NETTY, true);
    MinecraftVersion V20_3_PRE3 =
            MinecraftVersionImpl.of("1.20.3 Pre-Release 3", 1073741990, NETTY, true);
    MinecraftVersion V20_3_PRE4 =
            MinecraftVersionImpl.of("1.20.3 Pre-Release 4", 1073741991, NETTY, true);
    MinecraftVersion V20_3_RC1 =
            MinecraftVersionImpl.of("1.20.3 Release Candidate 1", 1073741992, NETTY, true);
    MinecraftVersion V20_3 = MinecraftVersionImpl.of("1.20.3", 765, NETTY);
    MinecraftVersion V20_4_RC1 =
            MinecraftVersionImpl.of("1.20.4 Release Candidate 1", 1073741993, NETTY, true);
    MinecraftVersion V20_4 = MinecraftVersionImpl.of("1.20.4", 765, NETTY);
    MinecraftVersion V23W51A = MinecraftVersionImpl.of("23w51a", 1073741993, NETTY, true);
    MinecraftVersion V23W51B = MinecraftVersionImpl.of("23w51b", 1073741994, NETTY, true);
    MinecraftVersion V24W03A = MinecraftVersionImpl.of("24w03a", 1073741995, NETTY, true);
    MinecraftVersion V24W03B = MinecraftVersionImpl.of("24w03b", 1073741996, NETTY, true);
    MinecraftVersion V24W05A = MinecraftVersionImpl.of("24w05a", 1073741997, NETTY, true);
    MinecraftVersion V24W04A = MinecraftVersionImpl.of("24w04a", 1073741997, NETTY, true);
    MinecraftVersion V24W05B = MinecraftVersionImpl.of("24w05b", 1073741999, NETTY, true);
    MinecraftVersion V24W06A = MinecraftVersionImpl.of("24w06a", 1073742000, NETTY, true);
    MinecraftVersion V24W07A = MinecraftVersionImpl.of("24w07a", 1073742001, NETTY, true);
    MinecraftVersion V24W09A = MinecraftVersionImpl.of("24w09a", 1073742002, NETTY, true);
    MinecraftVersion V24W10A = MinecraftVersionImpl.of("24w10a", 1073742003, NETTY, true);
    MinecraftVersion V24W11A = MinecraftVersionImpl.of("24w11a", 1073742004, NETTY, true);
    MinecraftVersion V24W12A = MinecraftVersionImpl.of("24w12a", 1073742005, NETTY, true);
    MinecraftVersion V24W13A = MinecraftVersionImpl.of("24w13a", 1073742006, NETTY, true);
    MinecraftVersion V24W14_POTATO =
            MinecraftVersionImpl.of("24w14potato", 1073742007, APRIL_FOOLS, true);
    MinecraftVersion V24W14A = MinecraftVersionImpl.of("24w14a", 1073742008, NETTY, true);
    MinecraftVersion V20_5_PRE1 =
            MinecraftVersionImpl.of("1.20.5 Pre-Release 1", 1073742009, NETTY, true);
    MinecraftVersion V20_5_PRE2 =
            MinecraftVersionImpl.of("1.20.5 Pre-Release 2", 1073742010, NETTY, true);
    MinecraftVersion V20_5_PRE3 =
            MinecraftVersionImpl.of("1.20.5 Pre-Release 3", 1073742011, NETTY, true);
    MinecraftVersion V20_5_PRE4 =
            MinecraftVersionImpl.of("1.20.5 Pre-Release 4", 1073742012, NETTY, true);
    MinecraftVersion V20_5_RC1 =
            MinecraftVersionImpl.of("1.20.5 Release Candidate 1", 1073742013, NETTY, true);
    MinecraftVersion V20_5_RC2 =
            MinecraftVersionImpl.of("1.20.5 Release Candidate 2", 1073742014, NETTY, true);
    MinecraftVersion V20_5_RC3 =
            MinecraftVersionImpl.of("1.20.5 Release Candidate 3", 1073742015, NETTY, true);
    MinecraftVersion V20_5 = MinecraftVersionImpl.of("1.20.5", 766, NETTY);
    MinecraftVersion V20_6_RC1 =
            MinecraftVersionImpl.of("1.20.6 Release Candidate 1", 1073742016, NETTY, true);
    MinecraftVersion V20_6 = MinecraftVersionImpl.of("1.20.6", 766, NETTY);
    MinecraftVersion V24W18A = MinecraftVersionImpl.of("24w18a", 1073742017, NETTY, true);
    MinecraftVersion V24W19A = MinecraftVersionImpl.of("24w19a", 1073742018, NETTY, true);
    MinecraftVersion V24W19B = MinecraftVersionImpl.of("24w19b", 1073742019, NETTY, true);
    MinecraftVersion V24W20A = MinecraftVersionImpl.of("24w20a", 1073742020, NETTY, true);
    MinecraftVersion V24W21A = MinecraftVersionImpl.of("24w21a", 1073742021, NETTY, true);
    MinecraftVersion V24W21B = MinecraftVersionImpl.of("24w21b", 1073742022, NETTY, true);
    MinecraftVersion V21_PRE1 =
            MinecraftVersionImpl.of("1.21 Pre-Release 1", 1073742023, NETTY, true);
    MinecraftVersion V21_PRE2 =
            MinecraftVersionImpl.of("1.21 Pre-Release 2", 1073742024, NETTY, true);
    MinecraftVersion V21_PRE3 =
            MinecraftVersionImpl.of("1.21 Pre-Release 3", 1073742025, NETTY, true);
    MinecraftVersion V21_PRE4 =
            MinecraftVersionImpl.of("1.21 Pre-Release 4", 1073742026, NETTY, true);
    MinecraftVersion V21_RC1 =
            MinecraftVersionImpl.of("1.21 Release Candidate 1", 1073742027, NETTY, true);
    MinecraftVersion V21 = MinecraftVersionImpl.of("1.21", 767, NETTY);
    MinecraftVersion V21_1_RC1 =
            MinecraftVersionImpl.of("1.21.1 Release Candidate 1", 1073742028, NETTY, true);
    MinecraftVersion V21_1 = MinecraftVersionImpl.of("1.21.1", 767, NETTY);
    MinecraftVersion V24W33A = MinecraftVersionImpl.of("24w33a", 1073742029, NETTY, true);
    MinecraftVersion V24W34A = MinecraftVersionImpl.of("24w34a", 1073742030, NETTY, true);
    MinecraftVersion V24W35A = MinecraftVersionImpl.of("24w35a", 1073742031, NETTY, true);
    MinecraftVersion V24W36A = MinecraftVersionImpl.of("24w36a", 1073742032, NETTY, true);
    MinecraftVersion V24W37A = MinecraftVersionImpl.of("24w37a", 1073742033, NETTY, true);
    MinecraftVersion V24W38A = MinecraftVersionImpl.of("24w38a", 1073742034, NETTY, true);
    MinecraftVersion V24W39A = MinecraftVersionImpl.of("24w39a", 1073742035, NETTY, true);
    MinecraftVersion V24W40A = MinecraftVersionImpl.of("24w40a", 1073742036, NETTY, true);
    MinecraftVersion V21_2_PRE1 =
            MinecraftVersionImpl.of("1.21.2 Pre-Release 1", 1073742037, NETTY, true);
    MinecraftVersion V21_2_PRE2 =
            MinecraftVersionImpl.of("1.21.2 Pre-Release 2", 1073742038, NETTY, true);
    MinecraftVersion V21_2_PRE3 =
            MinecraftVersionImpl.of("1.21.2 Pre-Release 3", 1073742039, NETTY, true);
    MinecraftVersion V21_2_PRE4 =
            MinecraftVersionImpl.of("1.21.2 Pre-Release 4", 1073742040, NETTY, true);
    MinecraftVersion V21_2_PRE5 =
            MinecraftVersionImpl.of("1.21.2 Pre-Release 5", 1073742041, NETTY, true);
    MinecraftVersion V21_2_RC1 =
            MinecraftVersionImpl.of("1.21.2 Release Candidate 1", 1073742042, NETTY, true);
    MinecraftVersion V21_2_RC2 =
            MinecraftVersionImpl.of("1.21.2 Release Candidate 2", 1073742043, NETTY, true);
    MinecraftVersion V21_2 = MinecraftVersionImpl.of("1.21.2", 768, NETTY);
    MinecraftVersion V21_3 = MinecraftVersionImpl.of("1.21.3", 768, NETTY);
    MinecraftVersion V24W44A = MinecraftVersionImpl.of("24w44a", 1073742044, NETTY, true);
    MinecraftVersion V24W45A = MinecraftVersionImpl.of("24w45a", 1073742045, NETTY, true);
    MinecraftVersion V24W46A = MinecraftVersionImpl.of("24w46a", 1073742046, NETTY, true);
}
