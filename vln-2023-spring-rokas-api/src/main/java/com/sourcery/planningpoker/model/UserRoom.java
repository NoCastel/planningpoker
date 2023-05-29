package com.sourcery.planningpoker.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class UserRoom {

  @Min(1)
  private int userRoomId;

  @NotEmpty
  private Integer roomId;

  @NotEmpty
  private Integer userId;

  @NotEmpty
  private Integer roleId;

  @Override
  public String toString() {
    return "UserRoom{" +
        "userRoomId=" + userRoomId +
        ", roomId=" + roomId +
        ", userId=" + userId +
        ", roleId=" + roleId +
        '}';
  }

  public UserRoom() {
  }

  public UserRoom(int userRoomId, Integer roomId, Integer userId, Integer roleId) {
    this.userRoomId = userRoomId;
    this.roomId = roomId;
    this.userId = userId;
    this.roleId = roleId;
  }

  public int getUserRoomId() {
    return userRoomId;
  }

  public void setUserRoomId(int userRoomId) {
    this.userRoomId = userRoomId;
  }

  public Integer getRoomId() {
    return roomId;
  }

  public void setRoomId(Integer roomId) {
    this.roomId = roomId;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Integer getRoleId() {
    return roleId;
  }

  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }
}
