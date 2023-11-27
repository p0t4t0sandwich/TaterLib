package dev.neuralnexus.taterlib.common;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class Utils {
    /**
     * Run a task asynchronously.
     *
     * @param run: The task to run asynchronously.
     */
    public static void runTaskAsync(Runnable run) {
        ForkJoinPool.commonPool().submit(run);
    }

    /**
     * Run a task asynchronously, after a delay.
     *
     * @param run: The task to run asynchronously.
     * @param delay: The delay in ticks to wait before running the task.
     */
    public static void runTaskLaterAsync(Runnable run, Long delay) {
        ForkJoinPool.commonPool()
                .submit(
                        () -> {
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
     *
     * @param run The task to run asynchronously.
     * @param delay The delay in seconds to wait before running the task.
     * @param period The period in seconds to repeat the task.
     */
    public static ForkJoinTask<Object> repeatTaskAsync(Runnable run, Long delay, Long period) {
        return ForkJoinPool.commonPool()
                .submit(
                        () -> {
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
     * A parser to convert Minecraft's color codes to the terminal's ANSI color codes.
     *
     * @param s The string to parse.
     * @return The parsed string.
     */
    public static String ansiParser(String s) {
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
     * Parse the section sign.
     *
     * @return The string with the section sign.
     */
    public static String substituteSectionSign(String s) {
        return s.replaceAll("&", "§");
    }
}
