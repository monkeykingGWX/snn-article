package com.snn.article.domain;

public class WebInfo {
    private Long webId;

    private String title;

    private String keyword;

    private String description;

    private String bottom;

    public Long getWebId() {
        return webId;
    }

    public void setWebId(Long webId) {
        this.webId = webId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getBottom() {
        return bottom;
    }

    public void setBottom(String bottom) {
        this.bottom = bottom == null ? null : bottom.trim();
    }
}