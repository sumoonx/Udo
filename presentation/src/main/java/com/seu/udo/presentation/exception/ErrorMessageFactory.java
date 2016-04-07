/*
 * Copyright (c) 2016. Team Yohe from Southeast University
 * All rights reserved.
 */

package com.yohe.presentation.exception;

import android.content.Context;

/**
 * Author: Jeremy Xu on 2016/3/29 17:32
 * E-mail: jeremy_xm@163.com
 */
public class ErrorMessageFactory {

    private ErrorMessageFactory() {}

    //TODO: Add all exceptions you want to support
    public static String create(Context context, Exception exception) {
        String message = "There was an application error.";

//        if (exception instanceof NetworkConnectionException) {
//            message = context.getString(R.string.exception_message_no_connection);
//        } else if (exception instanceof UserNotFoundException) {
//            message = context.getString(R.string.exception_message_user_not_found);
//        }

        return message;
    }
}
