package com.sourcery.planningpoker.dto;

public class UserDto {

  private int userId;

  private String userName;

  private String email;

  public UserDto() {
  }

  public UserDto(int userId, String userName, String email) {
    this.userId = userId;
    this.userName = userName;
    this.email = email;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
