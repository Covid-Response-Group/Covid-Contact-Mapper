package com.wordingly.covidcontacttracer.objects;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Posts extends RealmObject {
    public static final String POST_TYPE_DEFAULT = "default";
    public static final String POST_TYPE_WEB_LINK = "web_link";
    public static final String POST_TYPE_YOUTUBE_VIDEO = "youtube_video";
    @SerializedName("id")
    @PrimaryKey
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("subtitle")
    private String subtitle;

    @SerializedName("body")
    private String body;

    @SerializedName("duration")
    private String duration;

    @SerializedName("media_url")
    private String media_url;

    @SerializedName("post_type")
    private String post_type;

    @SerializedName("web_url")
    private String web_url;

    @SerializedName("is_web_only")
    private boolean is_web_only;

    @SerializedName("is_pinned")
    private boolean is_pinned;

    @SerializedName("created_at")
    private long created_at;

    @SerializedName("updated_at")
    private long updated_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getMedia_url() {
        return media_url;
    }

    public void setMedia_url(String media_url) {
        this.media_url = media_url;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public boolean is_web_only() {
        return is_web_only;
    }

    public void setIs_web_only(boolean is_web_only) {
        this.is_web_only = is_web_only;
    }

    public boolean is_pinned() {
        return is_pinned;
    }

    public void setIs_pinned(boolean is_pinned) {
        this.is_pinned = is_pinned;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }

    public long getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(long updated_at) {
        this.updated_at = updated_at;
    }

    public String getPost_type() {
        return post_type;
    }

    public void setPost_type(String post_type) {
        this.post_type = post_type;
    }
}
