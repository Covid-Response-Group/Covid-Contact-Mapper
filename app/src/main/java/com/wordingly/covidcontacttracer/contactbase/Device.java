package com.wordingly.covidcontacttracer.contactbase;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class Device {

    @SerializedName("deviceId")
    private String deviceId;

    @SerializedName("user")
    private Map<String, Object> user;

    public Device() {

    }

    public Device(String deviceId, Map<String, Object> user) {
        this.deviceId = deviceId;
        this.user = user;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Map<String, Object> getUser() {
        return user;
    }

    public void setUser(Map<String, Object> user) {
        this.user = user;
    }
}
