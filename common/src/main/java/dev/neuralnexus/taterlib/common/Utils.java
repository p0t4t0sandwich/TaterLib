package dev.neuralnexus.taterlib.common;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class Utils {
    /**
     * Run a task asynchronously.
     * @param run: The task to run asynchronously.
     */
    public static void runTaskAsync(Runnable run) {
        ForkJoinPool.commonPool().submit(run);
    }

    /**
     * Run a task asynchronously, after a delay.
     * @param run: The task to run asynchronously.
     * @param delay: The delay in ticks to wait before running the task.
     */
    public static void runTaskLaterAsync(Runnable run, Long delay) {
        ForkJoinPool.commonPool().submit(() -> {
            try {
                Thread.sleep(delay * 1000 / 20);
            } catch (InterruptedException e) {
                System.err.println(e);
                e.printStackTrace();
            }
            run.run();
        });
    }

    /**
     * Run a task asynchronously, repeating it every period seconds.
     * @param run The task to run asynchronously.
     * @param delay The delay in seconds to wait before running the task.
     * @param period The period in seconds to repeat the task.
     */
    public static ForkJoinTask<Object> repeatTaskAsync(Runnable run, Long delay, Long period) {
         return ForkJoinPool.commonPool().submit(() -> {
            try {
                Thread.sleep(delay * 1000 / 20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (true) {
                try {
                    Thread.sleep(period * 1000 / 20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                run.run();
            }
        });
    }

    /**
     * A parser to convert Minecraft's color codes to the terminal's ANSII color codes.
     * @param s The string to parse.
     * @return The parsed string.
     */
    public static String ansiiParser(String s) {
        // Colors

        // Black
        return s.replaceAll("§0", "\u001b[30m")
                // Dark Blue
                .replaceAll("§1", "\u001b[34m")
                // Dark Green
                .replaceAll("§2", "\u001b[32m")
                // Dark Aqua
                .replaceAll("§3", "\u001b[36m")
                // Dark Red
                .replaceAll("§4", "\u001b[31m")
                // Dark Purple
                .replaceAll("§5", "\u001b[35m")
                // Gold
                .replaceAll("§6", "\u001b[33m")
                // Gray
                .replaceAll("§7", "\u001b[37m")
                // Dark Gray
                .replaceAll("§8", "\u001b[90m")
                // Blue
                .replaceAll("§9", "\u001b[94m")
                // Green
                .replaceAll("§a", "\u001b[92m")
                // Aqua
                .replaceAll("§b", "\u001b[96m")
                // Red
                .replaceAll("§c", "\u001b[91m")
                // Light Purple
                .replaceAll("§d", "\u001b[95m")
                // Yellow
                .replaceAll("§e", "\u001b[93m")
                // White
                .replaceAll("§f", "\u001b[97m")

                // Formatting

                // Obfuscated
                .replaceAll("§k", "\u001b[5m")
                // Bold
                .replaceAll("§l", "\u001b[1m")
                // Strikethrough
                .replaceAll("§m", "\u001b[9m")
                // Underline
                .replaceAll("§n", "\u001b[4m")
                // Italic
                .replaceAll("§o", "\u001b[3m")
                // Reset
                .replaceAll("§r", "\u001b[0m")
                + "\u001b[0m";
    }

    /**
     * Check if the server is running CraftBukkit.
     * @return True if the server is running a fork of CraftBukkit, false otherwise.
     */
    public static boolean isCraftBukkit() {
        try {
            Class.forName("org.bukkit.craftbukkit.Main");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Check if the server is running Spigot.
     * @return True if the server is running a fork of Spigot, false otherwise.
     */
    public static boolean isSpigot() {
        try {
            Class.forName("org.spigotmc.CustomTimingsHandler");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Check if the server is running Paper.
     * @return True if the server is running a fork of Paper, false otherwise.
     */
    public static boolean isPaper() {
        try {
            Class.forName("com.destroystokyo.paper.PaperConfig");
            return true;
        } catch (ClassNotFoundException ignored) {}
        try {
            Class.forName("io.papermc.paperclip.Paperclip");
            return true;
        } catch (ClassNotFoundException ignored) {}
        return false;
    }

    /**
     * Check if the server is running Folia.
     * @return True if the server is running Folia, false otherwise.
     */
    public static boolean isFolia() {
        try {
            Class.forName("io.papermc.paper.threadedregions.RegionizedServer");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Check if the server is running Magma.
     * @return True if the server is running Magma, false otherwise.
     */
    public static boolean isMagma() {
        try {
            Class.forName("org.magmafoundation.magma.Magma");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Check if the server is running Mohist.
     * @return True if the server is running Mohist, false otherwise.
     */
    public static boolean isMohist() {
        try {
            Class.forName("org.mohistmc.MohistMC");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Check if the server is running Arclight.
     * @return True if the server is running Arclight, false otherwise.
     */
    public static boolean isArclight() {
        try {
            Class.forName("io.izzel.arclight.common.ArclightVersion");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static String getBukkitServerType() {
        if (isMagma()) {
            return "Magma";
        } else if (isMohist()) {
            return "Mohist";
        } else if (isArclight()) {
            return "Arclight";
        } else if (isFolia()) {
            return "Folia";
        } else if (isPaper()) {
            return "Paper";
        } else if (isSpigot()) {
            return "Spigot";
        } else if (isCraftBukkit()) {
            return "CraftBukkit";
        } else {
            return "Unknown";
        }
    }
}
