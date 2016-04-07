/*
 * Copyright (c) 2016. Team Udo from Southeast University
 * All rights reserved.
 */

package com.seu.udo.domain.interactor;

import com.seu.udo.domain.Credential;
import com.seu.udo.domain.executor.PostExecutionThread;
import com.seu.udo.domain.executor.ThreadExecutor;
import com.seu.udo.domain.repository.UserRepository;
import com.seu.udo.domain.response.Response;

import javax.inject.Inject;

import rx.Observable;

/**
 * Author: Jeremy Xu on 2016/4/5 21:11
 * E-mail: jeremy_xm@163.com
 */
public class DoLogin extends UseCase {

    private Credential credential;
    private final UserRepository userRepository;

    /**
     * Set all request related data through this constructor.
     *
     * @param userRepository where we can fetch user data.
     */
    @Inject
    public DoLogin(UserRepository userRepository,
                   ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
        credential = Credential.getCheckInstance();
    }

    public DoLogin credential(Credential credential) {
        this.credential = credential;
        return this;
    }

    public DoLogin credential(String account, String password) {
        credential = new Credential(account, password);
        return this;
    }

    /**
     * Request data from {@link UserRepository}.
     */
    @Override
    protected Observable<Response> buildUseCaseObservable() {
        if (credential == null) {
            throw new NullPointerException("Wrong Argument!");
        }
        Credential tmp = credential;
        credential = null;
        return userRepository.doLogin(tmp);
    }
}
