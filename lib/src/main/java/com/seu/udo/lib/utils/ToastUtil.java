/*
 * Copyright (c) 2016. Team Udo from Southeast University
 * All rights reserved.
 */

package com.seu.udo.lib.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

/**
 * Author: Jeremy Xu on 2016/4/5 14:45
 * E-mail: jeremy_xm@163.com
 */
public class ToastUtil {

    private static Toast toast = null;

    private static Handler handler = new Handler(Looper.getMainLooper());

    private static Runnable runnable = new Runnable() {
        @Override
        public void run() {
            toast.cancel();
        }
    };

    public static void show(final Context context, final String text) {
        handler.removeCallbacks(runnable);
        if (toast != null) {
            toast.setText(text);
        } else {
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        }
        handler.postDelayed(runnable, 5000);
        toast.show();
    }

    public static void show(final Context context, int strId) {
        show(context, context.getString(strId));
    }

    public static void showLong(final Context context, final String text) {
        handler.removeCallbacks(runnable);
        if (toast != null) {
            toast.setText(text);
        } else {
            toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        }
        handler.postDelayed(runnable, 5000);
        toast.show();
    }

    public static void showLong(final Context context, int strId) {
        showLong(context, context.getString(strId));
    }

    public static void cancel() {
        handler.removeCallbacks(runnable);
        if (toast != null) {
            toast.cancel();
        }
    }
}
