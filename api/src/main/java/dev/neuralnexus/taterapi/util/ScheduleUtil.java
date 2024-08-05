/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.util;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

@SuppressWarnings({"resource", "unused", "CallToPrintStackTrace"})
public class ScheduleUtil {
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
}
