package com.sourcery.planningpoker.service;

import com.sourcery.planningpoker.dto.RoomDto;
import com.sourcery.planningpoker.dto.mapper.RoomDtoMapper;
import com.sourcery.planningpoker.exceptions.ExceptionBuilder;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sourcery.planningpoker.interfaces.service.RoomServiceInterface;
import com.sourcery.planningpoker.mapper.RoomMapper;
import com.sourcery.planningpoker.model.Room;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomService implements RoomServiceInterface {

  @Autowired
  private RoomMapper roomMapper;

  @Autowired
  UserRoomService userRoomService;

  @Autowired
  private RoomDtoMapper roomDtoMapper;

  @Override
  public List<RoomDto> getAllRooms() {
    List<Room> rooms = roomMapper.getAllRooms();
    return rooms.stream().map(roomDtoMapper).collect(Collectors.toList());
  }

  @Override
  public RoomDto getRoomById(int roomId) {
    return roomMapper.getRoomById(roomId).map(roomDtoMapper).orElse(null);
  }

  @Override
  public void addRoom(Room room) throws ExceptionBuilder {
    Optional<Room> existingRoom = roomMapper.getRoomById(room.getRoomId());

    if (existingRoom.isPresent()) {
      throw new ExceptionBuilder("Room already exists", HttpStatus.CONFLICT);
    }

    if (room.getRoomName() == null
        || room.getRoomName().length() == 0
        || room.getTeamName() == null
        || room.getTeamName().length() == 0
        || room.getSprintName() == null
        || room.getSprintName().length() == 0) {
      throw new ExceptionBuilder("Only field \"Description\" is optional", HttpStatus.CONFLICT);
    }

    roomMapper.addRoom(room);
  }

  @Override
  @Transactional
  public void updateRoom(int roomId,
                         int userId,
                         String sprintName,
                         String teamName,
                         String description,
                         Integer deckId,
                         List<Integer> participantIdList) throws ExceptionBuilder {
    Room foundRoom = roomMapper.getRoomById(roomId).orElse(null);
    if (foundRoom == null) {
      throw new ExceptionBuilder("The room is not found.", HttpStatus.NOT_FOUND);
    }

    if (userId != foundRoom.getCreatorId()) {
      throw new ExceptionBuilder("You don't have rights to edit the room.", HttpStatus.UNAUTHORIZED);
    }

    if (sprintName != null
        && sprintName.length() > 0
        && !Objects.equals(sprintName, foundRoom.getSprintName())) {
      foundRoom.setSprintName(sprintName);
    } else if (Objects.equals(sprintName, foundRoom.getSprintName())) {
      throw new ExceptionBuilder("Sprint name must differ.", HttpStatus.CONFLICT);
    }

    if (teamName != null
        && teamName.length() > 0
        && !Objects.equals(teamName, foundRoom.getTeamName())) {
      foundRoom.setTeamName(teamName);
    } else if (Objects.equals(teamName, foundRoom.getTeamName())) {
      throw new ExceptionBuilder("Team name must differ.", HttpStatus.CONFLICT);
    }

    if (description != null
        && description.length() > 0
        && !Objects.equals(description, foundRoom.getDescription())) {
      foundRoom.setDescription(description);
    } else if (Objects.equals(description, foundRoom.getDescription())) {
      throw new ExceptionBuilder("Description must differ.", HttpStatus.CONFLICT);
    }

    if (deckId != null
        && !Objects.equals(deckId, foundRoom.getDeckId())) {
      foundRoom.setDeckId(deckId);
    } else if (Objects.equals(deckId, foundRoom.getDeckId())) {
      throw new ExceptionBuilder("Deck must differ.", HttpStatus.CONFLICT);
    }
    if (participantIdList != null) {
      userRoomService.updateUserRoomUsers(participantIdList, roomId, userId);
    }
    roomMapper.updateRoom(foundRoom);
  }

  @Override
  @Transactional
  public void deleteRoom(int roomId, int userId) throws ExceptionBuilder {
    Optional<Room> room = roomMapper.getRoomById(roomId);
    Map<String, String> message = new HashMap<>();

    if (room.isEmpty()) {
      throw new ExceptionBuilder("The room is not found.", HttpStatus.NOT_FOUND);
    }

    if (room.get().getCreatorId() != userId) {
      throw new ExceptionBuilder("You don't have rights to delete this room.", HttpStatus.UNAUTHORIZED);
    }
    roomMapper.deleteRoom(roomId);
  }

}
