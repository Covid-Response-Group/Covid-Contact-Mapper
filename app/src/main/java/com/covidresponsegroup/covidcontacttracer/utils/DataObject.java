package com.covidresponsegroup.covidcontacttracer.utils;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class DataObject {

    public String id;
    public String location;
    public long timestamp;

    public DataObject() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public DataObject(String id, String location) {
        this.id = id;
        this.location = location;
        this.timestamp = System.currentTimeMillis();
    }

}