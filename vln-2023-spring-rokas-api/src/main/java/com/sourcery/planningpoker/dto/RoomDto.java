package com.sourcery.planningpoker.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class RoomDto {

  private int roomId;
  @NotEmpty
  private String roomName;
  @NotEmpty
  private String teamName;
  @NotEmpty
  private String sprintName;

  private String description;

  @NotNull
  @AssertTrue
  private Boolean isActive;

  @Min(1)
  private int creatorId;

  @Min(1)
  private Integer deckId;


  public RoomDto() {
  }

  public RoomDto(int roomId,
                 String roomName,
                 String teamName,
                 String sprintName,
                 String description,
                 Boolean isActive,
                 int creatorId,
                 Integer deckId) {
    this.roomId = roomId;
    this.roomName = roomName;
    this.teamName = teamName;
    this.sprintName = sprintName;
    this.description = description;
    this.isActive = isActive;
    this.creatorId = creatorId;
    this.deckId = deckId;
  }

  public int getRoomId() {
    return roomId;
  }

  public void setRoomId(int roomId) {
    this.roomId = roomId;
  }

  public String getRoomName() {
    return roomName;
  }

  public void setRoomName(String roomName) {
    this.roomName = roomName;
  }

  public String getTeamName() {
    return teamName;
  }

  public void setTeamName(String teamName) {
    this.teamName = teamName;
  }

  public String getSprintName() {
    return sprintName;
  }

  public void setSprintName(String sprintName) {
    this.sprintName = sprintName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Boolean getActive() {
    return isActive;
  }

  public void setActive(Boolean active) {
    isActive = active;
  }

  public int getCreatorId() {
    return creatorId;
  }

  public void setCreatorId(int creatorId) {
    this.creatorId = creatorId;
  }

  public Integer getDeckId() {
    return deckId;
  }

  public void setDeckId(Integer deckId) {
    this.deckId = deckId;
  }
}
