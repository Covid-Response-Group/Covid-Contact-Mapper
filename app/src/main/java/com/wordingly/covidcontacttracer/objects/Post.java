package com.wordingly.covidcontacttracer.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Post extends RealmObject {
    public static final String POST_TYPE_DEFAULT = "default";
    public static final String POST_TYPE_WEB_LINK = "web_link";
    public static final String POST_TYPE_YOUTUBE_VIDEO = "youtube_video";


    @SerializedName("id")
    @PrimaryKey
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("subtitle")
    @Expose
    private String subtitle;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("media_url")
    @Expose
    private String mediaUrl;
    @SerializedName("web_url")
    @Expose
    private String webUrl;
    @SerializedName("is_web_only")
    @Expose
    private Boolean isWebOnly;
    @SerializedName("is_pinned")
    @Expose
    private Boolean isPinned;
    @SerializedName("created_at")
    @Expose
    private Integer createdAt;
    @SerializedName("updated_at")
    @Expose
    private Integer updatedAt;
    @SerializedName("post_type")
    @Expose
    private String postType;

    /**
     * No args constructor for use in serialization
     *
     */
    public Post() {
    }

    /**
     *
     * @param duration
     * @param createdAt
     * @param mediaUrl
     * @param isPinned
     * @param postType
     * @param webUrl
     * @param subtitle
     * @param id
     * @param title
     * @param body
     * @param isWebOnly
     * @param updatedAt
     */
    public Post(Integer id, String title, String subtitle, String body, String duration, String mediaUrl, String webUrl, Boolean isWebOnly, Boolean isPinned, Integer createdAt, Integer updatedAt, String postType) {
        super();
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.body = body;
        this.duration = duration;
        this.mediaUrl = mediaUrl;
        this.webUrl = webUrl;
        this.isWebOnly = isWebOnly;
        this.isPinned = isPinned;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.postType = postType;
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

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public Boolean getIsWebOnly() {
        return isWebOnly;
    }

    public void setIsWebOnly(Boolean isWebOnly) {
        this.isWebOnly = isWebOnly;
    }

    public Boolean getIsPinned() {
        return isPinned;
    }

    public void setIsPinned(Boolean isPinned) {
        this.isPinned = isPinned;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Integer updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

}