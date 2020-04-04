package com.wordingly.covidcontacttracer.objects;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DownloadData {

    @SerializedName("profile")
    @Expose
    private Profile profile;
    @SerializedName("posts")
    @Expose
    private List<Post> posts = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public DownloadData() {
    }

    /**
     *
     * @param profile
     * @param posts
     */
    public DownloadData(Profile profile, List<Post> posts) {
        super();
        this.profile = profile;
        this.posts = posts;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

}