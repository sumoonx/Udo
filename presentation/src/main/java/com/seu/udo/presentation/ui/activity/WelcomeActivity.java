/*
 * Copyright (c) 2016. Team Udo from Southeast University
 * All rights reserved.
 */

package com.seu.udo.presentation.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.seu.udo.R;

/**
 * Author: Jeremy Xu on 2016/4/5 20:01
 * E-mail: jeremy_xm@163.com
 */
public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navigator.toMain(WelcomeActivity.this);
        finish();
    }

    @Override
    protected int getLayout() {
        return R.layout.main;
    }
}
