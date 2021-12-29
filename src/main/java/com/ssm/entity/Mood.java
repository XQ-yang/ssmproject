package com.ssm.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Mood)实体类
 *
 * @author makejava
 * @since 2021-08-21 16:19:20
 */
public class Mood implements Serializable {
    private static final long serialVersionUID = -40922350935134617L;
    /**
     * 说说id
     */
    private String id;
    /**
     * 说说内容
     */
    private String content;
    /**
     * 发布者id
     */
    private String userId;
    /**
     * 发布时间
     */
    private Date publishTime;
    /**
     * 点赞数量
     */
    private Integer praiseNum;


    private String account;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Mood{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", userId='" + userId + '\'' +
                ", publishTime=" + publishTime +
                ", praiseNum=" + praiseNum +
                '}';
    }
}
