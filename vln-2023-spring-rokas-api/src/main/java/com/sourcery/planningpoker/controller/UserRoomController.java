package com.sourcery.planningpoker.controller;

import com.sourcery.planningpoker.model.UserRoom;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sourcery.planningpoker.model.Room;
import com.sourcery.planningpoker.service.UserRoomService;
import java.util.List;

@RestController
@Validated
@CrossOrigin
@RequestMapping("api/v1/users-rooms")
public class UserRoomController {

  @Autowired
  private UserRoomService userRoomService;

  @GetMapping("/{userId}")
  public ResponseEntity<List<Room>> getCreatedRooms(@PathVariable @Min(1) int userId) {
    List<Room> ownedRooms = userRoomService.getOwnedRooms(userId);
    return ownedRooms.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(ownedRooms);
  }

  @GetMapping("/get-participation-rooms/{userId}")
  public ResponseEntity<List<Room>> getParticipationRooms(@PathVariable @Min(1) int userId) {
    List<Room> participationRooms = userRoomService.getParticipationRooms(userId);
    return participationRooms.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(participationRooms);
  }

  @GetMapping("/user/{userId}/room/{roomId}")
  public ResponseEntity<UserRoom> getUserRoomByUserIdAndRoomId(@PathVariable @Min(1) Integer userId,
      @PathVariable @Min(1) Integer roomId) {
    UserRoom userRoom = userRoomService.getUserRoomByUserIdRoomId(userId, roomId);
    return userRoom != null ? ResponseEntity.ok(userRoom) : ResponseEntity.noContent().build();
  }

  @GetMapping("/room-role/{roomId}")
  public ResponseEntity<Integer> getRoomUserRoleByIds(@PathVariable @Min(1) Integer roomId, @RequestHeader("userId") Integer userId) {
    Integer userRole = userRoomService.getRoomUserRoleByIds(userId, roomId);
    return userRole != null ? ResponseEntity.ok(userRole) : ResponseEntity.status(401).build();
    // return userRole != null ? ResponseEntity.ok(userRole) : ResponseEntity.status(203).body(- 1);
    // return userRole != null ? ResponseEntity.ok(userRole) :
    // ResponseEntity.noContent(). build();

  }

  @GetMapping("/get-participant-user-ids/{roomId}")
  public ResponseEntity<List<Integer>> getParticipantUserIds(@PathVariable @Min(1) Integer roomId) {
    List<Integer> participantList = userRoomService.getParticipantUserIds(roomId);
    return participantList != null ? ResponseEntity.ok(participantList) : ResponseEntity.noContent().build();

  }

}
