package com.sourcery.planningpoker.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import com.sourcery.planningpoker.model.Vote;
import com.sourcery.planningpoker.dto.TempRoomDTO;
import com.sourcery.planningpoker.model.TempRoom;
import com.sourcery.planningpoker.model.TempUser;
import com.sourcery.planningpoker.model.UserInfo;
import com.sourcery.planningpoker.service.UserRoomService;

@Controller
public class WebsocketController {
    HashMap<Integer, TempRoom> rooms = new HashMap<>();

    @Autowired
    private UserRoomService userRoomService;

    WebsocketController() {
    }

    @MessageMapping("/websocket/story/{id}")
    @SendTo("/websocket/room/{id}")
    public TempRoomDTO wsSetStoryName(@DestinationVariable("id") Integer roomId, @Payload String storyName) {
        TempRoom room = rooms.get(roomId);
        room.setStoryName(storyName);
        room.setShowVote(false);
        for (Map.Entry<Integer, TempUser> user : room.getUsers().entrySet()) {
            user.getValue().setVote(null);
        }
        TempRoomDTO roomDTO = new TempRoomDTO(room);
        return roomDTO;
    }

    @MessageMapping("/websocket/vote/{id}")
    @SendTo("/websocket/room/{id}")
    public TempRoomDTO wsGetVote(@DestinationVariable("id") Integer roomId, @Payload Vote vote) {
        TempRoom room = rooms.get(roomId);
        if (!room.getStoryName().isEmpty() && !room.getShowVote())
            room.getUsers().get(vote.getUserId()).setVote(vote.getValue());
        TempRoomDTO roomDTO = new TempRoomDTO(room);
        return roomDTO;
    }

    @MessageMapping("/websocket/result/{id}")
    @SendTo("/websocket/room/{id}")
    public TempRoomDTO wsGetResult(@DestinationVariable("id") Integer roomId, @Payload Boolean showVote) {
        TempRoom room = rooms.get(roomId);
        if (!room.getStoryName().isEmpty()) {
            room.setShowVote(true);
        }
        TempRoomDTO roomDTO = new TempRoomDTO(room);
        return roomDTO;
    }

    @MessageMapping("/websocket/revote/{id}")
    @SendTo("/websocket/room/{id}")
    public TempRoomDTO wsRevote(@DestinationVariable("id") Integer roomId, @Payload Boolean showVote) {
        TempRoom room = rooms.get(roomId);
        room.setShowVote(false);
        for (Map.Entry<Integer, TempUser> user : room.getUsers().entrySet()) {
            user.getValue().setVote(null);
        }
        TempRoomDTO roomDTO = new TempRoomDTO(room);
        return roomDTO;
    }

    @MessageMapping("/websocket/reset/{id}")
    @SendTo("/websocket/room/{id}")
    public TempRoomDTO wsReset(@DestinationVariable("id") Integer roomId, @Payload String storyName) {
        TempRoom room = rooms.get(roomId);
        room.setShowVote(false);
        room.setStoryName("");
        for (Map.Entry<Integer, TempUser> user : room.getUsers().entrySet()) {
            user.getValue().setVote(null);
        }
        TempRoomDTO roomDTO = new TempRoomDTO(room);
        return roomDTO;
    }

    @MessageMapping("/websocket/users/{id}")
    @SendTo("/websocket/room/{id}")
    public TempRoomDTO wsGetUsers(@DestinationVariable("id") Integer roomId, @Payload Integer userId) {
        if (!rooms.containsKey(roomId)) {
            List<TempUser> userList = userRoomService.getRoomUserList(roomId);
            HashMap<Integer, TempUser> users = new HashMap<>();
            for (TempUser tempUser : userList) {
                users.put(tempUser.getUserId(), tempUser);
            }
            rooms.put(roomId, new TempRoom(roomId, users, false, ""));
        }
        rooms.get(roomId).getUsers().get(userId).setIsActive(true);
        TempRoomDTO roomDTO = new TempRoomDTO(rooms.get(roomId));
        return roomDTO;
    }

    @MessageMapping("/websocket/user-info/{id}")
    @SendTo("/websocket/room/{id}")
    public TempRoomDTO wsGetUserInfo(@DestinationVariable("id") Integer roomId, @Payload Integer userId) {
        if (!rooms.containsKey(roomId)) {
            List<TempUser> userList = userRoomService.getRoomUserList(roomId);
            HashMap<Integer, TempUser> users = new HashMap<>();
            for (TempUser tempUser : userList) {
                users.put(tempUser.getUserId(), tempUser);
            }
            rooms.put(roomId, new TempRoom(roomId, users, false, ""));
        }
        rooms.get(roomId).getUsers().get(userId).setIsActive(true);
        TempRoomDTO roomDTO = new TempRoomDTO(rooms.get(roomId));
        return roomDTO;
    }

    @MessageMapping("/websocket/disconnect/{id}")
    @SendTo("/websocket/room/{id}")
    public TempRoomDTO disconnectUser(@DestinationVariable("id") Integer roomId, @Payload Integer userId) {
        TempRoom room = rooms.get(roomId);
        room.getUsers().get(userId).setIsActive(false);
        TempRoomDTO roomDTO = new TempRoomDTO(room);
        return roomDTO;
    }

    @MessageMapping("/vote")
    @SendToUser // <- maps to "/user/queue/search"
    public Integer getCard(@Payload UserInfo userInfo) {
        TempRoom room = rooms.get(userInfo.getRoomId());
        if (room == null)
            return null;
        TempUser user = room.getUsers().get(userInfo.getUserId());
        if (user == null)
            return null;
        return user.getVote();
    }
    // @MessageMapping("/private")
    // @SendToUser // <- maps to "/user/queue/search"
    // public String search(@Payload UserInfo userInfo) {
    // // System.out.println("DSDS => " + roomId);
    // return "TEST1234";
    // }

}