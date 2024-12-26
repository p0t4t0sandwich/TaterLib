package dev.neuralnexus.modapi.metadata.enums;

import dev.neuralnexus.modapi.metadata.MinecraftVersions;

/**
 * Enum wrapper for use with annotations
 */
public enum MinecraftVersion {
    UNKNOWN(MinecraftVersions.UNKNOWN),
    V0(MinecraftVersions.V0),
    V0_1(MinecraftVersions.V0_1),
    V1(MinecraftVersions.V1),
    V2(MinecraftVersions.V2),
    V2_1(MinecraftVersions.V2_1),
    V2_2(MinecraftVersions.V2_2),
    V2_3(MinecraftVersions.V2_3),
    V2_4(MinecraftVersions.V2_4),
    V2_5(MinecraftVersions.V2_5),
    V3(MinecraftVersions.V3),
    V3_1(MinecraftVersions.V3_1),
    V3_2(MinecraftVersions.V3_2),
    V4(MinecraftVersions.V4),
    V4_1(MinecraftVersions.V4_1),
    V4_2(MinecraftVersions.V4_2),
    V4_3(MinecraftVersions.V4_3),
    V4_4(MinecraftVersions.V4_4),
    V4_5(MinecraftVersions.V4_5),
    V4_6(MinecraftVersions.V4_6),
    V4_7(MinecraftVersions.V4_7),
    V5(MinecraftVersions.V5),
    V5_1(MinecraftVersions.V5_1),
    V5_2(MinecraftVersions.V5_2),
    V6(MinecraftVersions.V6),
    V6_1(MinecraftVersions.V6_1),
    V6_2(MinecraftVersions.V6_2),
    V6_3(MinecraftVersions.V6_3),
    V6_4(MinecraftVersions.V6_4),
    V7(MinecraftVersions.V7),
    V7_1(MinecraftVersions.V7_1),
    V7_2(MinecraftVersions.V7_2),
    V7_3(MinecraftVersions.V7_3),
    V7_4(MinecraftVersions.V7_4),
    V7_5(MinecraftVersions.V7_5),
    V7_6(MinecraftVersions.V7_6),
    V7_7(MinecraftVersions.V7_7),
    V7_8(MinecraftVersions.V7_8),
    V7_9(MinecraftVersions.V7_9),
    V7_10(MinecraftVersions.V7_10),
    V8(MinecraftVersions.V8),
    V8_1(MinecraftVersions.V8_1),
    V8_2(MinecraftVersions.V8_2),
    V8_3(MinecraftVersions.V8_3),
    V8_4(MinecraftVersions.V8_4),
    V8_5(MinecraftVersions.V8_5),
    V8_6(MinecraftVersions.V8_6),
    V8_7(MinecraftVersions.V8_7),
    V8_8(MinecraftVersions.V8_8),
    V8_9(MinecraftVersions.V8_9),
    V9(MinecraftVersions.V9),
    V9_1(MinecraftVersions.V9_1),
    V9_2(MinecraftVersions.V9_2),
    V9_3(MinecraftVersions.V9_3),
    V9_4(MinecraftVersions.V9_4),
    V10(MinecraftVersions.V10),
    V10_1(MinecraftVersions.V10_1),
    V10_2(MinecraftVersions.V10_2),
    V11(MinecraftVersions.V11),
    V11_1(MinecraftVersions.V11_1),
    V11_2(MinecraftVersions.V11_2),
    V12(MinecraftVersions.V12),
    V12_1(MinecraftVersions.V12_1),
    V12_2(MinecraftVersions.V12_2),
    V13(MinecraftVersions.V13),
    V13_1(MinecraftVersions.V13_1),
    V13_2(MinecraftVersions.V13_2),
    V14(MinecraftVersions.V14),
    V14_1(MinecraftVersions.V14_1),
    V14_2(MinecraftVersions.V14_2),
    V14_3(MinecraftVersions.V14_3),
    V14_4(MinecraftVersions.V14_4),
    V15(MinecraftVersions.V15),
    V15_1(MinecraftVersions.V15_1),
    V15_2(MinecraftVersions.V15_2),
    V16(MinecraftVersions.V16),
    V16_1(MinecraftVersions.V16_1),
    V16_2(MinecraftVersions.V16_2),
    V16_3(MinecraftVersions.V16_3),
    V16_4(MinecraftVersions.V16_4),
    V16_5(MinecraftVersions.V16_5),
    V17(MinecraftVersions.V17),
    V17_1(MinecraftVersions.V17_1),
    V18(MinecraftVersions.V18),
    V18_1(MinecraftVersions.V18_1),
    V18_2(MinecraftVersions.V18_2),
    V19(MinecraftVersions.V19),
    V19_1(MinecraftVersions.V19_1),
    V19_2(MinecraftVersions.V19_2),
    V19_3(MinecraftVersions.V19_3),
    V19_4(MinecraftVersions.V19_4),
    V20(MinecraftVersions.V20),
    V20_1(MinecraftVersions.V20_1),
    V20_2(MinecraftVersions.V20_2),
    V20_3(MinecraftVersions.V20_3),
    V20_4(MinecraftVersions.V20_4),
    V20_5(MinecraftVersions.V20_5),
    V20_6(MinecraftVersions.V20_6),
    V21(MinecraftVersions.V21),
    V21_1(MinecraftVersions.V21_1),
    V21_2(MinecraftVersions.V21_2),
    V21_3(MinecraftVersions.V21_3),
    V21_4(MinecraftVersions.V21_4);

    private final dev.neuralnexus.modapi.metadata.MinecraftVersion ref;

    MinecraftVersion(dev.neuralnexus.modapi.metadata.MinecraftVersion ref) {
        this.ref = ref;
    }

    /**
     * Get the reference to the version
     *
     * @return The version reference
     */
    public dev.neuralnexus.modapi.metadata.MinecraftVersion ref() {
        return ref;
    }
}
