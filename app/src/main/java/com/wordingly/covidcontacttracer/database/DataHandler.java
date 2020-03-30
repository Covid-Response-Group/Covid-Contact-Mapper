package com.wordingly.covidcontacttracer.database;

import android.app.Activity;
import android.app.Application;

import androidx.fragment.app.Fragment;


import io.realm.Realm;

public class DataHandler {

    private static DataHandler mInstance;
    private final Realm mRealm;
    private static final String TAG = DataHandler.class.getSimpleName();

    public DataHandler(Application application) {
        mRealm = Realm.getDefaultInstance();
    }

    public static DataHandler with(Fragment fragment) {

        if (mInstance == null) {
            mInstance = new DataHandler(fragment.getActivity().getApplication());
        }
        return mInstance;
    }

    public static DataHandler with(Activity activity) {

        if (mInstance == null) {
            mInstance = new DataHandler(activity.getApplication());
        }
        return mInstance;
    }

    public static DataHandler with(Application application) {

        if (mInstance == null) {
            mInstance = new DataHandler(application);
        }
        return mInstance;
    }

    public static DataHandler getInstance() {

        return mInstance;
    }

    public Realm getRealm() {
        return mRealm;
    }


//    public Word getWord(int parentCardId) {
//        return mRealm.where(Word.class).equalTo("parent_cards.card_id", parentCardId).findFirst();
//    }
//
//
//    public Cards getStoryCards(String id, int position){
//        return mRealm.where(Stories.class).equalTo("id", id).findFirst().getCards().get(position);
//    }
//
//    public void clearUser() {
//        mRealm.beginTransaction();
//        mRealm.where(User.class).findAll().deleteAllFromRealm();
//        mRealm.commitTransaction();
//    }
}