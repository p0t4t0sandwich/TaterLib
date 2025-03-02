/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.impl.loader;

import dev.neuralnexus.taterapi.meta.impl.util.ReflectionUtil;

public final class CheckForBad {
    /** Check if the user is using TLauncher, because piracy is bad. */
    public static boolean checkForTLauncher() {
        return ReflectionUtil.checkForClass(
                "org.tlauncher.MixinConnector", "org.tlauncher.tlauncher.rmo.Bootstrapper");
    }

    /**
     * Check for the Bright SDK, because we don't want them to steal data. <a
     * href="https://github.com/ctrlaltmilk/HandsOffMyData/blob/1.20.1/src/main/resources/coremods/handsoffmydata.js#L3-L34">Thanks
     * ctrlaltmilk for the list of classes</a>
     */
    public static boolean checkForBrightSDK() {
        return ReflectionUtil.checkForClass(
                "com.amplitude.exception.AmplitudeException",
                "com.amplitude.exception.AmplitudeInvalidAPIKeyException",
                "com.amplitude.Amplitude",
                "com.amplitude.AmplitudeCallbacks",
                "com.amplitude.AmplitudeLog",
                "com.amplitude.AmplitudeLog$LogMode",
                "com.amplitude.Constants",
                "com.amplitude.Event",
                "com.amplitude.EventsRetryResult",
                "com.amplitude.HttpCall",
                "com.amplitude.HttpCallMode",
                "com.amplitude.HttpTransport",
                "com.amplitude.HttpTransport$RetryEventsOnLoop",
                "com.amplitude.HttpTransport$SendEventsTask",
                "com.amplitude.IngestionMetadata",
                "com.amplitude.Middleware",
                "com.amplitude.MiddlewareExtra",
                "com.amplitude.MiddlewareNext",
                "com.amplitude.MiddlewarePayload",
                "com.amplitude.MiddlewareRunner",
                "com.amplitude.Options",
                "com.amplitude.Plan",
                "com.amplitude.Response",
                "com.amplitude.Status",
                "com.amplitude.Utils",
                "com.brightsdk.Device",
                "com.brightsdk.Main",
                "com.brightsdk.SessionTracker",
                "com.brightsdk.Storage");
    }
}
