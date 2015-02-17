package com.zorz.mario.api;


import com.zorz.mario.model.CoverLetterResponse;
import com.zorz.mario.model.ProjectsResponse;

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



    public static class AndroidProjectsLoadStartEvent {

    }

    public static class AndroidProjectsLoadFailEvent extends Event<Error> {

        public AndroidProjectsLoadFailEvent(Error object) {
            super(object);
        }
    }

    public static class AndroidProjectsLoadCompleteEvent extends Event<ProjectsResponse> {

        public AndroidProjectsLoadCompleteEvent(ProjectsResponse object) {
            super(object);
        }
    }


    public static class OtherProjectsLoadStartEvent {

    }

    public static class OtherProjectsLoadFailEvent extends Event<Error> {

        public OtherProjectsLoadFailEvent(Error object) {
            super(object);
        }
    }

    public static class OtherProjectsLoadCompleteEvent extends Event<ProjectsResponse> {

        public OtherProjectsLoadCompleteEvent(ProjectsResponse object) {
            super(object);
        }
    }

}