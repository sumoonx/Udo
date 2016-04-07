/*
 * Copyright (c) 2016. Team Udo from Southeast University
 * All rights reserved.
 */

package com.seu.udo.presentation.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.seu.udo.presentation.internal.di.HasComponent;
import com.seu.udo.presentation.navigation.Navigator;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Author: Jeremy Xu on 2016/4/5 20:23
 * E-mail: jeremy_xm@163.com
 */
public abstract class BaseFragment extends Fragment {

    @Inject Navigator navigator;

    /**
     * Gets a component for dependency injection by its type.
     */
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }
}
