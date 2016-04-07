/*
 * Copyright (c) 2016. Team Udo from Southeast University
 * All rights reserved.
 */

package com.seu.udo.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Author: Jeremy Xu on 2016/4/5 20:01
 * E-mail: jeremy_xm@163.com
 */
public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navigator.toLogin(WelcomeActivity.this);
        finish();
    }
}
