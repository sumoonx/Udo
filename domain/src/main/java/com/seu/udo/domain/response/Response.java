/*
 * Copyright (c) 2016. Team Udo from Southeast University
 * All rights reserved.
 */

package com.seu.udo.domain.response;

/**
 * Author: Jeremy Xu on 2016/4/5 21:16
 * E-mail: jeremy_xm@163.com
 */
public class Response {
    boolean success;

    public Response(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}
