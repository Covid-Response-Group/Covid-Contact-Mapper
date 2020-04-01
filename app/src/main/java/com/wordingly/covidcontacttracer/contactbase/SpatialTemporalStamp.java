package com.wordingly.covidcontacttracer.contactbase;

import com.google.gson.annotations.SerializedName;

public class SpatialTemporalStamp {

    @SerializedName("geohash")
    private String geohash;

    @SerializedName("dateStamp")
    private String dateStamp;

    public SpatialTemporalStamp() {

    }

    public SpatialTemporalStamp(String geohash, String dateStamp) {
        this.geohash = geohash;
        this.dateStamp = dateStamp;
    }

    public String getGeohash() {
        return geohash;
    }

    public void setGeohash(String geohash) {
        this.geohash = geohash;
    }

    public String getDateStamp() {
        return dateStamp;
    }

    public void setDateStamp(String dateStamp) {
        this.dateStamp = dateStamp;
    }
}
