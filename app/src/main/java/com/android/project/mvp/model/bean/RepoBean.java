package com.android.project.mvp.model.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author Finn
 * @date 2020
 */
public class RepoBean {

    private int id;

    private String name;

    @SerializedName("private")
    private Boolean privateX;

    private OwnerBean owner;

    private String html_url;

    private String description;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getPrivateX() {
        return privateX;
    }

    public OwnerBean getOwner() {
        return owner;
    }

    public String getHtml_url() {
        return html_url;
    }

    public String getDescription() {
        return description;
    }

    public String getAvatar() {
        if (getOwner() != null) {
            return getOwner().getAvatar_url();
        }
        return "";
    }

    public static class OwnerBean {
        private int id;

        private String name;

        private String avatar_url;

        private String url;

        private String html_url;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public String getUrl() {
            return url;
        }

        public String getHtml_url() {
            return html_url;
        }
    }

}
