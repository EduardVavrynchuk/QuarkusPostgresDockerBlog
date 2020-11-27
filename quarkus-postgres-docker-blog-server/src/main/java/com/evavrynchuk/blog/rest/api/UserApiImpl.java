package com.evavrynchuk.blog.rest.api;

import com.evavrynchuk.blog.api.UserApi;
import com.evavrynchuk.blog.model.UserResponse;
import javax.inject.Singleton;

@Singleton
public class UserApiImpl implements UserApi {

    public UserApiImpl() {

    }

    @Override
    public UserResponse addUser(String username) {
        return null;
    }
}
