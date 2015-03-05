package com.zorz.mario.api;


import retrofit.RequestInterceptor;

public class SessionRequestInterceptor implements RequestInterceptor {

    @Override
    public void intercept(RequestFacade request) {
        request.addQueryParam("user_key", "539ee6c63ff32");
        request.addQueryParam("api_key", "d428e76ae9228cae6f5ed9dd3e9b3ce9b3e9acf2");
    }
}
