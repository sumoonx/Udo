package com.seu.udo.domain;

/**
 * Author: Jeremy Xu on 2016/4/6 10:35
 * E-mail: jeremy_xm@163.com
 */
public class Credential {
    private final String username;

    private final String password;

    private final boolean checkLogin;

    private Credential(boolean checkLogin) {
        username = null;
        password = null;
        this.checkLogin = checkLogin;
    }

    public Credential(String username, String password) {
        this.username = username;
        this.password = password;
        checkLogin = false;
    }

    public static Credential getCheckInstance() {
        return new Credential(true);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isCheckLogin() {
        return checkLogin;
    }
}
