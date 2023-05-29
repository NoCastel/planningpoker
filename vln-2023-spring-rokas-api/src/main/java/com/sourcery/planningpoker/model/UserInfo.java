package com.sourcery.planningpoker.model;

public class UserInfo {
    private int userId;
    private int roomId;

    UserInfo() {
    }

    UserInfo(int userId, int roomId) {
        this.userId = userId;
        this.roomId = roomId;
    }

    public int getUserId() {
        return userId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
}
