package com.wordingly.covidcontacttracer.objects;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestPosts extends RealmObject{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;

    /**
     * No args constructor for use in serialization
     *
     */
    public TestPosts() {
    }

    /**
     *
     * @param id
     * @param title
     */
    public TestPosts(Integer id, String title) {
        super();
        this.id = id;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}