package com.covidresponse.covidcontacttracer;

import android.app.Application;

import com.covidresponse.covidcontacttracer.utils.Prefs;

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