package com.sourcery.planningpoker.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class User {
  private int id;

  @NotEmpty
  private String userName;

  @Email
  @NotEmpty
  private String email;

  @NotEmpty
  private String password;

  public User(String userName, String email, String password) {
    this.userName = userName;
    this.email = email;
    this.password = password;
  }

  public int getUserId() {
    return id;
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public User() {
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", userName='" + userName + '\'' +
        ", email='" + email + '\'' +
        ", password='" + password + '\'' +
        '}';
  }
}
