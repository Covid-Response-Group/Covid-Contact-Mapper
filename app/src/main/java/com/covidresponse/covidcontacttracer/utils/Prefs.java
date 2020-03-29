package com.covidresponse.covidcontacttracer.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.covidresponse.covidcontacttracer.CovidContactTracer;

public class Prefs {


    private static final String PREFS_NAME = "prefs";
    private static final String IS_NOTIFICATION_LOCATION_DISABLED = "is_notif_loc_disabled";
    static final String KEY_REQUESTING_LOCATION_UPDATES = "requesting_location_updates";
    static final String GOOGLE_ACCOUNT_ID = "google_account_id";

    private static SharedPreferences.Editor editor = null;
    private static SharedPreferences sp = null;


    private static SharedPreferences.Editor getEditor() {
        if (editor == null) {
            editor = getSP().edit();
        }
        return editor;
    }

    public static void init(Context context) {
        if (context != null) {
            sp = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            editor = sp.edit();
        }
    }

    private static SharedPreferences getSP() {
        if (sp == null) {
            init(CovidContactTracer.getInstance());
        }
        return sp;
    }

    public static void clearAll() {
        getEditor().clear();
        getEditor().commit();
    }

    public static boolean requestingLocationUpdates(Context context) {
        return getSP().getBoolean(KEY_REQUESTING_LOCATION_UPDATES, false);
    }

    public static void setRequestingLocationUpdates(boolean requestingLocationUpdates) {
        getEditor().putBoolean(KEY_REQUESTING_LOCATION_UPDATES, requestingLocationUpdates).apply();
    }

    public static void setGoogleAccountId(String googleAccountId) {
        getEditor().putString(GOOGLE_ACCOUNT_ID, googleAccountId).apply();
    }

    public static String getGoogleAccountId(){
        return getSP().getString(GOOGLE_ACCOUNT_ID, null);
    }

}