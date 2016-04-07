package com.seu.udo.presentation.internal.di;

/**
 * Interface representing a contract for clients that contains a component for dependency injection.
 *
 * Author: Jeremy Xu on 2016/4/6 15:20
 * E-mail: jeremy_xm@163.com
 */
public interface HasComponent<C> {
    C getComponent();
}
