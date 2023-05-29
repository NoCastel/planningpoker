package com.sourcery.planningpoker.model;

import java.util.HashMap;
import java.util.Map;

public class TempRoom {
    private Integer roomId;
    private HashMap<Integer, TempUser> users = new HashMap<>();
    private Boolean showVote;
    private String storyName;

    public TempRoom() {
    }

    public TempRoom(Integer roomId, HashMap<Integer, TempUser> users, Boolean showVote, String storyName) {
        this.roomId = roomId;
        this.users = users;
        this.showVote = showVote;
        this.storyName = storyName;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public HashMap<Integer, TempUser> getUsers() {
        return users;
    }

    public Boolean getShowVote() {
        return showVote;
    }

    public Float getAverage() {
        int count = 0;
        int sum = 0;
        for (Map.Entry<Integer, TempUser> user : users.entrySet()) {
            if (user.getValue().getVote() != null) {
                sum += user.getValue().getVote();
                count++;
            }
        }
        return count > 0 ? ((float) sum / count) : null;
    }

    public String getStoryName() {
        return storyName;
    }

    public void setUsers(HashMap<Integer, TempUser> users) {
        this.users = users;
    }

    public void setShowVote(Boolean showVote) {
        this.showVote = showVote;
    }

    public void setStoryName(String storyName) {
        this.storyName = storyName;
    }

}
