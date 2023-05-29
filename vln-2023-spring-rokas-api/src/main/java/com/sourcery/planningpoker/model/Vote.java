package com.sourcery.planningpoker.model;

public class Vote {
    private Integer userId;
    private String userName;
    private Integer value;

    public Vote() {
    }

    public Vote(Integer userId, String userName, Integer value) {
        this.userId = userId;
        this.userName = userName;
        this.value = value;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getValue() {
        return value;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
