package com.zorz.mario.api;


import retrofit.RequestInterceptor;

public class SessionRequestInterceptor implements RequestInterceptor {

    @Override
    public void intercept(RequestFacade request) {
        request.addQueryParam("user_key", "539ee886cqw");
        request.addQueryParam("api_key", "d428e76ae101010e6f5ed9dd3e9b3ce9b3e9ac10");
    }
}
