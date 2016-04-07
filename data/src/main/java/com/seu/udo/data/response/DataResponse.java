package com.seu.udo.data.response;

import com.seu.udo.domain.response.Response;

/**
 * Author: Jeremy Xu on 2016/4/6 10:26
 * E-mail: jeremy_xm@163.com
 */
public class DataResponse {
    boolean success;

    public DataResponse(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}
