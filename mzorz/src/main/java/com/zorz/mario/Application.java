package com.zorz.mario;


import de.greenrobot.event.EventBus;

/**
 * Created by mariozorz on 1/15/15.
 */
public class Application extends android.app.Application {

    private static final String TAG = "Application";

    private static Application _instance;

    public Application() {
        super();
        _instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static Application get() {
        return _instance;
    }

    public static EventBus getEventBus() {
        return EventBus.getDefault();
    }

}

