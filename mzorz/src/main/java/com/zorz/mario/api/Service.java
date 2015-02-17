package com.zorz.mario.api;

/**
 * Created by mariozorz on 1/15/15.
 */

import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zorz.mario.Application;
import com.zorz.mario.api.definition.IApiService;
import com.zorz.mario.model.CoverLetterResponse;
import com.zorz.mario.model.ProjectsResponse;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;


public class Service {

    private static final Service instance = new Service();

    private static final String TAG = Service.class.getName();

    public static final String END_POINT = "http://192.168.0.21:8080/mzorzcv/api/v1";

    private static Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create();


    private static IApiService service = new RestAdapter.Builder()
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .setEndpoint(END_POINT)
            .setConverter(new GsonConverter(gson))
            .setRequestInterceptor(new SessionRequestInterceptor())
            .build().create(IApiService.class);

    public void getCoverLetter() {

        Application.getEventBus().post(new Event.CoverLetterLoadStartEvent());

        service.getCoverLetter(new Callback<CoverLetterResponse>() {

            @Override
            public void success(CoverLetterResponse resp, Response response) {
                Application.getEventBus().post(new Event.CoverLetterLoadCompleteEvent(resp));
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.i(TAG, retrofitError.getMessage());
                if (retrofitError.getResponse() != null) {
                    try {
                        Error error = (Error) retrofitError.getBodyAs(Error.class);
                        Application.getEventBus().post(new Event.CoverLetterLoadFailEvent(error));
                    } catch (Exception ex){
                        ex.printStackTrace();
                        Application.getEventBus().post(new Event.CoverLetterLoadFailEvent(null));
                    }
                } else {
                    Application.getEventBus().post(new Event.CoverLetterLoadFailEvent(null));
                }
            }
        });
    }



    public void getAndroidProjects() {

        Application.getEventBus().post(new Event.AndroidProjectsLoadStartEvent());

        service.getAndroidProjects(new Callback<ProjectsResponse>() {

            @Override
            public void success(ProjectsResponse resp, Response response) {
                Application.getEventBus().post(new Event.AndroidProjectsLoadCompleteEvent(resp));
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.i(TAG, retrofitError.getMessage());
                if (retrofitError.getResponse() != null) {
                    try {
                        Error error = (Error) retrofitError.getBodyAs(Error.class);
                        Application.getEventBus().post(new Event.AndroidProjectsLoadFailEvent(error));
                    } catch (Exception ex){
                        ex.printStackTrace();
                        Application.getEventBus().post(new Event.AndroidProjectsLoadFailEvent(null));
                    }
                } else {
                    Application.getEventBus().post(new Event.AndroidProjectsLoadFailEvent(null));
                }
            }
        });
    }



    public static Service getInstance() {
        return instance;
    }


}
