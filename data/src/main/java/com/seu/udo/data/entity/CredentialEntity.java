/*
 * Copyright (c) 2016. Team Udo from Southeast University
 * All rights reserved.
 */

package com.seu.udo.data.entity;

/**
 * Author: Jeremy Xu on 2016/4/5 21:32
 * E-mail: jeremy_xm@163.com
 */
public class CredentialEntity {
    private final String username;

    private final String password;

    public CredentialEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
