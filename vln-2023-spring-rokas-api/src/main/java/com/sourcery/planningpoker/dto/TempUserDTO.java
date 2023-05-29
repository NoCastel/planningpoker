package com.sourcery.planningpoker.dto;

import com.sourcery.planningpoker.model.TempUser;

public class TempUserDTO {
    private Integer userId;
    private String username;
    private Integer vote;
    private Integer role;
    private Boolean voteStatus;

    TempUserDTO() {
    }

    TempUserDTO(TempUser user, Boolean showVote) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.role = user.getRole();
        this.vote = showVote ? user.getVote() : null;
        this.voteStatus = user.getVote() != null ? true : false;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public Integer getVote() {
        return vote;
    }

    public Integer getRole() {
        return role;
    }

    public Boolean getVoteStatus() {
        return voteStatus;
    }
}
