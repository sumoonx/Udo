package com.seu.udo.domain.repository;

import com.seu.udo.domain.Credential;
import com.seu.udo.domain.response.Response;

import rx.Observable;

/**
 * Author: Jeremy Xu on 2016/4/6 10:20
 * E-mail: jeremy_xm@163.com
 */
public interface UserRepository {

    Observable<Response> doLogin(Credential credentialEntity);

}
