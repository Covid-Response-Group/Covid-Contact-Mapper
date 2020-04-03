package com.wordingly.covidcontacttracer;

import android.app.Application;

import androidx.multidex.MultiDexApplication;

import com.wordingly.covidcontacttracer.utils.Prefs;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import com.onesignal.OneSignal;
public class CovidContactTracer extends MultiDexApplication {

    public static final String TAG = CovidContactTracer.class.getSimpleName();
    private static CovidContactTracer mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        Prefs.init(this);
        initOneSignal();
        initRealm();
        mInstance = this;
    }

    private void initRealm() {
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    private void initOneSignal() {
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
    }




    public static synchronized CovidContactTracer getInstance() {
        return mInstance;
    }




}