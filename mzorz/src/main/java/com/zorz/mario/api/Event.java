package com.zorz.mario.api;


import com.zorz.mario.model.CoverLetterResponse;

/**
 * Created by mariozorz on 1/15/15.
 */
public class Event<T> {
    public final T object;

    public Event(T object) {
        this.object = object;
    }

    public static class CoverLetterLoadStartEvent {

    }

    public static class CoverLetterLoadFailEvent extends Event<Error> {

        public CoverLetterLoadFailEvent(Error object) {
            super(object);
        }
    }

    public static class CoverLetterLoadCompleteEvent extends Event<CoverLetterResponse> {

        public CoverLetterLoadCompleteEvent(CoverLetterResponse object) {
            super(object);
        }
    }




}