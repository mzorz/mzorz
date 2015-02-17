package com.zorz.mario.api;


import com.zorz.mario.model.AboutAppResponse;
import com.zorz.mario.model.AboutMeResponse;
import com.zorz.mario.model.CoverLetterResponse;
import com.zorz.mario.model.ProjectsResponse;
import com.zorz.mario.model.WantToWorkOnResponse;

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


    public static class WantToWorkLoadStartEvent {

    }

    public static class WantToWorkLoadFailEvent extends Event<Error> {

        public WantToWorkLoadFailEvent(Error object) {
            super(object);
        }
    }

    public static class WantToWorkLoadCompleteEvent extends Event<WantToWorkOnResponse> {

        public WantToWorkLoadCompleteEvent(WantToWorkOnResponse object) {
            super(object);
        }
    }


    public static class AboutMeLoadStartEvent {

    }

    public static class AboutMeLoadFailEvent extends Event<Error> {

        public AboutMeLoadFailEvent(Error object) {
            super(object);
        }
    }

    public static class AboutMeLoadCompleteEvent extends Event<AboutMeResponse> {

        public AboutMeLoadCompleteEvent(AboutMeResponse object) {
            super(object);
        }
    }


    public static class AboutAppLoadStartEvent {

    }

    public static class AboutAppLoadFailEvent extends Event<Error> {

        public AboutAppLoadFailEvent(Error object) {
            super(object);
        }
    }

    public static class AboutAppLoadCompleteEvent extends Event<AboutAppResponse> {

        public AboutAppLoadCompleteEvent(AboutAppResponse object) {
            super(object);
        }
    }

}