package com.wordingly.covidcontacttracer.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Registration extends RealmObject {

    @SerializedName("deviceId")
    @Expose
    private String deviceId;
    @SerializedName("user")
    @Expose
    private User user;

    /**
     * No args constructor for use in serialization
     *
     */
    public Registration() {
    }

    /**
     *
     * @param deviceId
     * @param user
     */
    public Registration(String deviceId, User user) {
        super();
        this.deviceId = deviceId;
        this.user = user;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}