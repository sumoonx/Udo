/*
 * Copyright (c) 2016. Team Udo from Southeast University
 * All rights reserved.
 */

package com.seu.udo.presentation.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.seu.udo.R;
import com.seu.udo.lib.utils.LogUtil;
import com.seu.udo.lib.utils.ToastUtil;

/**
 * Author: Jeremy Xu on 2016/4/5 16:22
 * E-mail: jeremy_xm@163.com
 */
public class TestActivity extends BaseActivity implements View.OnClickListener {

    private Button test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.test);

        test = (Button) findViewById(R.id.btn_test);
        test.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_test:
                LogUtil.i("Test","show this");
                ToastUtil.show(TestActivity.this, "I'm a Toast");
                break;
        }
    }
}
