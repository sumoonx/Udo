package com.seu.udo.data.repository;

import com.seu.udo.domain.Credential;
import com.seu.udo.domain.repository.UserRepository;
import com.seu.udo.domain.response.Response;
import com.seu.udo.lib.utils.LogUtil;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;

/**
 * Author: Jeremy Xu on 2016/4/6 10:22
 * E-mail: jeremy_xm@163.com
 */
public class DefaultUserRepository implements UserRepository {

    @Inject
    public DefaultUserRepository() {}

    @Override
    public Observable<Response> doLogin(Credential credential) {
        Observable<Response> observable;
        LogUtil.i("account is: " + credential.getUsername()
        + " and password is:" + credential.getPassword());
        if (credential.getUsername().equals("admin")
                && credential.getPassword().equals("admin")) {
            observable = Observable.just(new Response(true));
        } else {
            observable = Observable.just(new Response(false))
                    .delay(3000, TimeUnit.MILLISECONDS);
        }
        LogUtil.i("ready to return the Observable");
        return observable;
    }
}
