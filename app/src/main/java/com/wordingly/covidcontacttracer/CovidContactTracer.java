package com.wordingly.covidcontacttracer;

import android.app.Application;

import com.wordingly.covidcontacttracer.utils.Prefs;

public class CovidContactTracer extends Application {

    public static final String TAG = CovidContactTracer.class.getSimpleName();
    private static CovidContactTracer mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        Prefs.init(this);
        mInstance = this;
    }




    public static synchronized CovidContactTracer getInstance() {
        return mInstance;
    }




}