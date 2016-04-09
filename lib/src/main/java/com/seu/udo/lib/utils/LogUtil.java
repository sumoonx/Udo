/*
 * Copyright (c) 2016. Team Udo from Southeast University
 * All rights reserved.
 */

package com.seu.udo.lib.utils;

import android.util.Log;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * LogUtil is a log utility with log level.
 *
 * If you don't pass the tag argument, it will use the default tag.
 * Add prefix 'a' to the traditional log, LogUtil will provide an
 * advanced Log method.
 *
 * This LogUtil can also Log json or xml {@link String}, as well as
 * a {@link Throwable} Error.
 *
 * Author: Jeremy Xu on 2016/4/5 13:52
 * E-mail: jeremy_xm@163.com
 */
public class LogUtil {

    public static final int VERBOSE = 1;

    public static final int DEBUG = 2;

    public static final int INFO = 3;

    public static final int WARN = 4;

    public static final int ERROR = 5;

    public static final int NOTHING = 6;

    public static final int LEVEL = VERBOSE;

    public static final String DEFAULT_TAG = "Udo log";


    /**
     * Initialize the {@link Logger}
     */
    static {
        LogLevel level = LogLevel.FULL;
        if (LEVEL == NOTHING) {
            level = LogLevel.NONE;
        }
        Logger.init(DEFAULT_TAG)
                .logLevel(level)
                .methodOffset(1);
    }

    private LogUtil() {}

    /**
     * Log the message with a given tag, only if the level is VERBOSE.
     *
     * @param tag the tag to be shown.
     * @param message the message to be logged.
     */
    public static void v(String tag, String message) {
        if (LEVEL <= VERBOSE) {
            Log.v(tag, message);
        }
    }


    /**
     * Log the message with the default tag, only if the level is VERBOSE.
     *
     * @param message the message to be logged.
     */
    public static void v(String message) {
        v(DEFAULT_TAG, message);
    }


    /**
     * Log the message with a given tag, only if the level is
     * lower than DEBUG.
     *
     * @param tag the tag to be shown.
     * @param message the message to be logged.
     */
    public static void d(String tag, String message) {
        if (LEVEL <= DEBUG) {
            Log.d(tag, message);
        }
    }

    /**
     * Log the message with the default tag, only if the level is
     * lower than DEBUG.
     *
     * @param message the message to be logged.
     */
    public static void d(String message) {
        d(DEFAULT_TAG, message);
    }

    /**
     * Log the message with a given tag, only if the level is
     * lower than INFO.
     *
     * @param tag the tag to be shown.
     * @param message the message to be logged.
     */
    public static void i(String tag, String message) {
        if (LEVEL <= INFO) {
            Log.i(tag, message);
        }
    }

    /**
     * Log the message with a given tag, only if the level is
     * lower than INFO.
     *
     * @param message the message to be logged.
     */
    public static void i(String message) {
        i(DEFAULT_TAG, message);
    }

    /**
     * Log the message with a given tag, only if the level is
     * lower than WARN.
     *
     * @param tag the tag to be shown.
     * @param message the message to be logged.
     */
    public static void w(String tag, String message) {
        if (LEVEL <= WARN) {
            Log.w(tag, message);
        }
    }

    /**
     * Log the message with a given tag, only if the level is
     * lower than WARN.
     *
     * @param message the message to be logged.
     */
    public static void w(String message) {
        w(DEFAULT_TAG, message);
    }

    /**
     * Log the message with a given tag, only if the level is
     * lower than ERROR.
     *
     * @param tag the tag to be shown.
     * @param message the message to be logged.
     */
    public static void e(String tag, String message) {
        if (LEVEL <= ERROR) {
            Log.e(tag, message);
        }
    }

    /**
     * Log the message with a given tag, only if the level is
     * lower than ERROR.
     *.
     * @param message the message to be logged.
     */
    public static void e(String message) {
        e(DEFAULT_TAG, message);
    }

    /**
     * Log the message along with the {@link Throwable} in
     * a format like: "message : throwable.toString()",
     * only if the level is lower than ERROR.
     *
     * @param throwable the Error to be shown.
     * @param message the message to be logged.
     */
    public static void e(Throwable throwable, String message) {
        if (LEVEL <= ERROR) {
            Logger.e(throwable, message);
        }
    }

    /**
     * Advance-log the message with the default tag, only if the level is VERBOSE.
     *
     * @param message the message to be logged.
     */
    public static void av(String message) {
        if (LEVEL <= VERBOSE) {
            Logger.v(message);
        }
    }

    /**
     * Advanced-log the message with the default tag, only if the level is
     * lower than DEBUG.
     *
     * @param message the message to be logged.
     */
    public static void ad(String message) {
        if (LEVEL <= DEBUG) {
            Logger.d(message);
        }
    }

    /**
     * Advanced-log the message with the default tag, only if the level is
     * lower than INFO.
     *
     * @param message the message to be logged.
     */
    public static void ai(String message) {
        if (LEVEL <= INFO) {
            Logger.i(message);
        }
    }

    /**
     * Advanced-log the message with the default tag, only if the level is
     * lower than WARN.
     *
     * @param message the message to be logged.
     */
    public static void aw(String message) {
        if (LEVEL <= WARN) {
            Logger.w(message);
        }
    }

    /**
     * Advanced-log the message with the default tag, only if the level is
     * lower than ERROR.
     *
     * @param message the message to be logged.
     */
    public static void ae(String message) {
        if (LEVEL <= ERROR) {
            Logger.e( message);
        }
    }

    /**
     * Advanced-log the message with the default tag, only if the level is
     * lower than ERROR.
     *
     * @param throwable the Error to be shown.
     * @param message the message to be logged.
     */
    public static void ae(Throwable throwable, String message) {
        if (LEVEL <= ERROR) {
            Logger.e(throwable, message);
        }
    }

    public static void wtf(String message) {
        Logger.wtf(message);
    }

    /**
     * Log the json {@link String}
     *
     * @param json json String to be logged
     */
    public static void json(String json) {
        Logger.json(json);
    }

    /**
     * Log the xml {@link String}
     *
     * @param xml xml String to be logged
     */
    public static void xml(String xml) {
        Logger.xml(xml);
    }
}
