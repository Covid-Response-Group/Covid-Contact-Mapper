package com.wordingly.covidcontacttracer.contactbase;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Contact implements Serializable {

    @SerializedName("deviceId")
    private String deviceId;

    @SerializedName("spatialTemporalStamps")
    private List<SpatialTemporalStamp> spatialTemporalStamps;

    public Contact() {

    }

    public Contact(String deviceId, List<SpatialTemporalStamp> spatialTemporalStamps) {
        this.deviceId = deviceId;
        this.spatialTemporalStamps = spatialTemporalStamps;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public List<SpatialTemporalStamp> getSpatialTemporalStamps() {
        return spatialTemporalStamps;
    }

    public void setSpatialTemporalStamps(List<SpatialTemporalStamp> spatialTemporalStamps) {
        this.spatialTemporalStamps = spatialTemporalStamps;
    }
}
