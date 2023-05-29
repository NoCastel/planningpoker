package com.sourcery.planningpoker.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.Date;

public class Story {
  private Integer id;

  @NotNull
  private String name;

  @PastOrPresent
  @NotNull
  private Date date;
  // private LocalDate date;

  @NotNull
  private Integer estimate;

  @NotNull
  private Integer roomId;

  public Story(Integer id, String name, Date date, Integer estimate, Integer roomId) {
    this.id = id;
    this.name = name;
    this.date = date;
    this.estimate = estimate;
    this.roomId = roomId;
  }

  public Story() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Integer getEstimate() {
    return estimate;
  }

  public void setEstimate(Integer estimate) {
    this.estimate = estimate;
  }

  public Integer getRoomId() {
    return roomId;
  }

  public void setRoomId(Integer roomId) {
    this.roomId = roomId;
  }

  @Override
  public String toString() {
    return "Story{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", date=" + date +
        ", estimate=" + estimate +
        ", roomId=" + roomId +
        '}';
  }
}
