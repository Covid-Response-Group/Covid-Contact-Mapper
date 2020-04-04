package com.wordingly.covidcontacttracer.objects;

import io.realm.RealmObject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile extends RealmObject {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("risk_level")
    @Expose
    private String riskLevel;
    @SerializedName("close_contacts")
    @Expose
    private Integer closeContacts;
    @SerializedName("social_distancing_score")
    @Expose
    private Integer socialDistancingScore;

    /**
     * No args constructor for use in serialization
     *
     */
    public Profile() {
    }

    /**
     *
     * @param riskLevel
     * @param socialDistancingScore
     * @param closeContacts
     * @param userId
     */
    public Profile(String userId, String riskLevel, Integer closeContacts, Integer socialDistancingScore) {
        super();
        this.userId = userId;
        this.riskLevel = riskLevel;
        this.closeContacts = closeContacts;
        this.socialDistancingScore = socialDistancingScore;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public Integer getCloseContacts() {
        return closeContacts;
    }

    public void setCloseContacts(Integer closeContacts) {
        this.closeContacts = closeContacts;
    }

    public Integer getSocialDistancingScore() {
        return socialDistancingScore;
    }

    public void setSocialDistancingScore(Integer socialDistancingScore) {
        this.socialDistancingScore = socialDistancingScore;
    }

}