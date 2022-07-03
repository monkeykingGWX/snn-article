package com.snn.article.domain;

import java.util.Date;

public class Article {
    private Long articleId;

    private Long cateId;

    private String title;

    private String keyword;

    private Boolean isHot;

    private Boolean isFavor;

    private Boolean isSub;

    private Date createTime;

    private Boolean isDelete;

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getCateId() {
        return cateId;
    }

    public void setCateId(Long cateId) {
        this.cateId = cateId;
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

    public Boolean getIsHot() {
        return isHot;
    }

    public void setIsHot(Boolean isHot) {
        this.isHot = isHot;
    }

    public Boolean getIsFavor() {
        return isFavor;
    }

    public void setIsFavor(Boolean isFavor) {
        this.isFavor = isFavor;
    }

    public Boolean getIsSub() {
        return isSub;
    }

    public void setIsSub(Boolean isSub) {
        this.isSub = isSub;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
}