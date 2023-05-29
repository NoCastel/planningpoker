package com.sourcery.planningpoker.model;

public class TempUser {
    private Integer userId;
    private String username;
    private String email;
    private Integer role;
    private Boolean isActive = false;
    private Integer vote = null;
    // private Boolean voteStatus;

    public TempUser() {
    }


    public TempUser(Integer userId, String username, String email, Integer role) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.role = role;
        // this.isActive = false;
        // this.vote = null;
        // this.voteStatus = false;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Integer getRole() {
        return role;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public Integer getVote() {
        return vote;
    }

    // public Boolean getVoteStatus() {
    // return voteStatus;
    // }
    
    public void setRole(Integer role) {
        this.role = role;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    // public void setVoteStatus(Boolean voteStatus) {
    //     this.voteStatus = voteStatus;
    // }

    public void setVote(Integer vote) {
        this.vote = vote;
    }
}
