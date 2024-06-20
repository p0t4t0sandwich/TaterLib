package dev.neuralnexus.conditionalmixins;

import java.util.Arrays;

public class Utils {
    /**
     * Check if a class exists.
     *
     * @param className The class(es) to check.
     * @return Whether the class exists.
     */
    public static boolean reflectCheck(String... className) {
        return Arrays.stream(className)
                .anyMatch(
                        s -> {
                            try {
                                Class.forName(s);
                                return true;
                            } catch (ClassNotFoundException e) {
                                return false;
                            }
                        });
    }
}
