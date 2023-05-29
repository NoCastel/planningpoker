package com.sourcery.planningpoker.interfaces.service;

import com.sourcery.planningpoker.dto.RoomDto;
import com.sourcery.planningpoker.exceptions.ExceptionBuilder;

import com.sourcery.planningpoker.model.Room;
import java.util.List;

public interface RoomServiceInterface {

  List<RoomDto> getAllRooms();

  RoomDto getRoomById(int roomId);

  void addRoom(Room room) throws ExceptionBuilder;

  void updateRoom(int roomId,
                  int userId,
                  String sprintName,
                  String teamName,
                  String description,
                  Integer deckId,
                  List<Integer> participantIdList) throws ExceptionBuilder;

  void deleteRoom(int roomId, int userId) throws ExceptionBuilder;
}
