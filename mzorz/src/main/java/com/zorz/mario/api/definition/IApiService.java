package com.zorz.mario.api.definition;


/**
 * Created by mariozorz on 1/15/15.
 */

import com.zorz.mario.model.CoverLetterResponse;

import retrofit.Callback;
import retrofit.http.GET;

public interface IApiService {

    @GET("/coverletter")
    public void getCoverLetter(Callback<CoverLetterResponse> callback);

    @GET("/previouswork")
    public void getPreviousWork(Callback<CoverLetterResponse> callback);

//    @GET("/news")
//    public void getNews(Callback<NewsResponse> callback);
//
//    @GET("/events")
//    public void getEvents(Callback<EventsResponse> callback);
//
//    @GET("/artists")
//    public void getArtists(Callback<ArtistsResponse> callback);
//
}

