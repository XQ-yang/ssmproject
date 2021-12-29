package com.ssm.entity;

import java.io.Serializable;

/**
 * (UserMoodPraiseRel)实体类
 *
 * @author makejava
 * @since 2021-08-21 16:20:11
 */
public class UserMoodPraiseRel implements Serializable {
    private static final long serialVersionUID = 558312612077494462L;

    private Long id;
    /**
     * 发布者id
     */
    private String userId;
    /**
     * 说说id
     */
    private String moodId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMoodId() {
        return moodId;
    }

    public void setMoodId(String moodId) {
        this.moodId = moodId;
    }

    @Override
    public String toString() {
        return "UserMoodPraiseRel{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", moodId='" + moodId + '\'' +
                '}';
    }
}
