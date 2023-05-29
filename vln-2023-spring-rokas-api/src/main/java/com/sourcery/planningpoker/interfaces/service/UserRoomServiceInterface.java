package com.sourcery.planningpoker.interfaces.service;

import com.sourcery.planningpoker.exceptions.ExceptionBuilder;

import com.sourcery.planningpoker.model.Room;
import com.sourcery.planningpoker.model.TempUser;
import com.sourcery.planningpoker.model.UserRoom;

import java.util.List;

public interface UserRoomServiceInterface {

  void addRoomOwner(Room room) throws ExceptionBuilder;

  void addRoomParticipants(Room room) throws ExceptionBuilder;

  List<Room> getOwnedRooms(int userId);

  Integer getRoomUserRoleByIds(int userId, int roomId);

  List<Room> getParticipationRooms(int userId);

  UserRoom getUserRoomByUserIdRoomId(Integer userId, Integer roomId);

  void updateUserRoomUsers(List<Integer> userIdList, Integer roomId, Integer editorId) throws ExceptionBuilder;

  List<Integer> getParticipantUserIds(Integer roomId);

  List<TempUser> getRoomUserList(Integer roomId);
}
