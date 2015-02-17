package com.zorz.mario.api.definition;


/**
 * Created by mariozorz on 1/15/15.
 */

import com.zorz.mario.model.CoverLetterResponse;
import com.zorz.mario.model.ProjectsResponse;
import com.zorz.mario.model.WantToWorkOnResponse;

import retrofit.Callback;
import retrofit.http.GET;

public interface IApiService {

    @GET("/coverletter")
    public void getCoverLetter(Callback<CoverLetterResponse> callback);

    @GET("/projects/android")
    public void getAndroidProjects(Callback<ProjectsResponse> callback);

    @GET("/projects/other")
    public void getOtherProjects(Callback<ProjectsResponse> callback);

    @GET("/workon")
    public void getWantToWorkOn(Callback<WantToWorkOnResponse> callback);

}

