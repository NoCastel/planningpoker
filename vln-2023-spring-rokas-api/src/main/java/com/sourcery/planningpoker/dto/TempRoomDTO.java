package com.sourcery.planningpoker.dto;

import java.util.ArrayList;
import java.util.Map;

import com.sourcery.planningpoker.model.TempRoom;
import com.sourcery.planningpoker.model.TempUser;

public class TempRoomDTO {

    private Integer roomId;
    private String storyName;
    private Float average;
    private Boolean showVote;
    private ArrayList<TempUserDTO> users;

    public TempRoomDTO() {
    }

    public TempRoomDTO(TempRoom room) {
        this.roomId = room.getRoomId();
        this.storyName = room.getStoryName();
        this.average = room.getShowVote() ? room.getAverage() : null;
        this.showVote = room.getShowVote();
        ArrayList<TempUserDTO> tempUsers = new ArrayList<>();
        for (Map.Entry<Integer, TempUser> user : room.getUsers().entrySet()) {
            if (user.getValue().getIsActive()) 
                tempUsers.add(new TempUserDTO(user.getValue(), room.getShowVote()));
        }
        this.users = tempUsers;


    }

    public Integer getRoomId() {
        return roomId;
    }

    public ArrayList<TempUserDTO> getUsers() {
        return users;
    }

    public String getStoryName() {
        return storyName;
    }

    public Float getAverage() {
        return average;
    }

    public Boolean getShowVote() {
        return showVote;
    }
}
