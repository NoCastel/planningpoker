package com.sourcery.planningpoker.service;

import com.sourcery.planningpoker.exceptions.ExceptionBuilder;
import com.sourcery.planningpoker.mapper.RoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sourcery.planningpoker.interfaces.service.UserRoomServiceInterface;
import com.sourcery.planningpoker.mapper.RoleMapper;
import com.sourcery.planningpoker.mapper.UserMapper;
import com.sourcery.planningpoker.mapper.UserRoomMapper;
import com.sourcery.planningpoker.model.Role;
import com.sourcery.planningpoker.model.Room;
import com.sourcery.planningpoker.model.TempUser;
import com.sourcery.planningpoker.model.User;
import com.sourcery.planningpoker.model.UserRoom;

import java.util.List;
import java.util.Optional;

@Service
public class UserRoomService implements UserRoomServiceInterface {

  @Autowired
  private UserRoomMapper userRoomMapper;

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private RoleMapper roleMapper;

  @Autowired
  private RoomMapper roomMapper;

  @Override
  public void addRoomOwner(Room room) throws ExceptionBuilder {
    UserRoom userRoom = new UserRoom();
    userRoom.setRoomId(room.getRoomId());

    Optional<Role> ownerRole = roleMapper.findByRoleType("owner");
    Optional<User> user = userMapper.getUserById(room.getCreatorId());

    if (user.isPresent() && ownerRole.isPresent()) {
      userRoom.setUserId(user.get().getUserId());
      userRoom.setRoleId(ownerRole.get().getRoleId());

      userRoomMapper.addRoomOwner(userRoom);

    } else if (user.isEmpty() && ownerRole.isPresent()) {
      throw new ExceptionBuilder("User with provided id not found.", HttpStatus.NOT_FOUND);

    } else if (user.isPresent() && ownerRole.isEmpty()) {
      throw new ExceptionBuilder("Role owner not found.", HttpStatus.NOT_FOUND);

    } else {
      throw new ExceptionBuilder("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  public void addRoomParticipants(Room room) throws ExceptionBuilder {
    Optional<Role> participantRoleId = roleMapper.findByRoleType("participant");

    if (participantRoleId.isEmpty()) {
      throw new ExceptionBuilder("Participant role not found.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    for (Integer participantId : room.getInvitedUserIds()) {
      Optional<User> user = userMapper.getUserById(participantId);
      if (user.isPresent()) {
        userRoomMapper.addRoomParticipant(room.getRoomId(), participantId, participantRoleId.get().getRoleId());
      } else {
        throw new ExceptionBuilder("Some invited user does not exist.", HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
  }

  @Override
  public List<Room> getOwnedRooms(int userId) {
    return userRoomMapper.findOwnedRoomsByIdAndRole(userId, 1);
  }

  @Override
  public List<Room> getParticipationRooms(int userId) {
    return userRoomMapper.findOwnedRoomsByIdAndRole(userId, 3);
  }

  @Override
  public UserRoom getUserRoomByUserIdRoomId(Integer userId, Integer roomId) {
    return userRoomMapper.getUserRoomByUserIdRoomId(userId, roomId);
  }

  @Override
  public Integer getRoomUserRoleByIds(int userId, int roomId) {
    return userRoomMapper.getRoomUserRoleByIds(userId, roomId);
  }

  @Override
  public void updateUserRoomUsers(List<Integer> userIdList, Integer roomId, Integer editorId) throws ExceptionBuilder {
    // checking if the editor is the creator of the room
    UserRoom editorRoom = userRoomMapper.getUserRoomByUserIdRoomIdRoleId(editorId, roomId, 1);
    if (editorRoom == null) {
      throw new ExceptionBuilder("Editor is not the creator of the room.", HttpStatus.FORBIDDEN);
    }

    // delete existing participants (get all that has this room and role 3)
    List<UserRoom> userRoomForDeleting = userRoomMapper.getUserRoomsByRoomIdRoleId(roomId, 3);
    if (!userRoomForDeleting.isEmpty()) {
      for (UserRoom userRoom : userRoomForDeleting) {
        userRoomMapper.deleteRoomParticipantById(userRoom.getUserRoomId());
      }
    }

    // add new participants
    for (Integer userId : userIdList) {
      if (userMapper.getUserById(userId).isPresent() && roomMapper.getRoomById(roomId).isPresent()) {
        userRoomMapper.addRoomParticipant(roomId, userId, 3);
      } else {
        throw new ExceptionBuilder("Failed to add participant.", HttpStatus.CONFLICT);
      }
    }
  }

  @Override
  public List<Integer> getParticipantUserIds(Integer roomId) {
    return userRoomMapper.getParticipantUserIds(roomId);
  }

  @Override
  public List<TempUser> getRoomUserList(Integer roomId) {
    return userRoomMapper.getRoomUserList(roomId);
  }

}
