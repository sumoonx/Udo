/*
 * Copyright (c) 2016. Team Udo from Southeast University
 * All rights reserved.
 */

package com.seu.udo.lib.utils;

import android.util.Log;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
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

    static {
        LogLevel level = LogLevel.FULL;
        if (LEVEL == NOTHING) {
            level = LogLevel.NONE;
        }
        Logger.init("Udo log")
                .logLevel(level)
                .methodOffset(1);
    }

    private LogUtil() {}

    public static void v(String tag, String msg) {
        if (LEVEL <= VERBOSE) {
            Log.v(tag, msg);
        }
    }

    public static void v(String msg) {
        if (LEVEL <= VERBOSE) {
            Logger.v(msg);
        }
    }

    public static void d(String tag, String msg) {
        if (LEVEL <= DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void d(String msg) {
        if (LEVEL <= DEBUG) {
            Logger.d(msg);
        }
    }

    public static void i(String tag, String msg) {
        if (LEVEL <= INFO) {
            Log.i(tag, msg);
        }
    }

    public static void i(String msg) {
        if (LEVEL <= INFO) {
            Logger.i(msg);
        }
    }

    public static void w(String tag, String msg) {
        if (LEVEL <= WARN) {
            Log.w(tag, msg);
        }
    }

    public static void w(String msg) {
        if (LEVEL <= WARN) {
            Logger.w(msg);
        }
    }

    public static void e(String tag, String msg) {
        if (LEVEL <= ERROR) {
            Log.e(tag, msg);
        }
    }

    public static void e(String msg) {
        if (LEVEL <= ERROR) {
            Logger.e(msg);
        }
    }

    public static void e(Throwable throwable, String msg) {
        if (LEVEL <= ERROR) {
            Logger.e(throwable, msg);
        }
    }

    public static void wtf(String msg) {
        Logger.wtf(msg);
    }

    public static void json(String json) {
        Logger.json(json);
    }

    public static void xml(String xml) {
        Logger.xml(xml);
    }
}
