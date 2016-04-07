/*
 * Copyright (c) 2016. Team Udo from Southeast University
 * All rights reserved.
 */

package com.seu.udo.presentation.internal.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Author: Jeremy Xu on 2016/3/29 21:52
 * E-mail: jeremy_xm@163.com
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
