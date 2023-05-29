package com.sourcery.planningpoker.controller;

import java.util.List;
import java.util.Map;
import com.sourcery.planningpoker.dto.RoomDto;
import com.sourcery.planningpoker.dto.mapper.RoomDtoMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sourcery.planningpoker.exceptions.ExceptionBuilder;
import com.sourcery.planningpoker.handlers.response.ResponseMessageHandler;
import com.sourcery.planningpoker.service.UserRoomService;
import com.sourcery.planningpoker.model.Room;
import com.sourcery.planningpoker.service.RoomService;


@RestController
@Validated
@CrossOrigin
@RequestMapping("api/v1/rooms")
public class RoomController {

  @Autowired
  private RoomService roomService;

  @Autowired
  UserRoomService userRoomService;

  @Autowired
  private RoomDtoMapper roomDtoMapper;

  Map<String, String> response;

  @Autowired
  private ResponseMessageHandler responseMessageHandler;

  @GetMapping("/all-rooms")
  public ResponseEntity<List<RoomDto>> getAllRooms() {
    List<RoomDto> rooms = roomService.getAllRooms();
    return rooms.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(rooms);
  }

  @GetMapping("/{roomId}")
  public ResponseEntity<RoomDto> getRoomById(@PathVariable("roomId") @Min(1) int roomId) {
    RoomDto room = roomService.getRoomById(roomId);
    return room != null ? ResponseEntity.ok(room) : ResponseEntity.notFound().build();
  }

  @PostMapping("/add")
  public ResponseEntity<Object> registerNewRoom(@Valid @RequestBody Room room
  ) {
    try {
      roomService.addRoom(room);
      userRoomService.addRoomOwner(room);
      userRoomService.addRoomParticipants(room);
      RoomDto filteredRoom = roomDtoMapper.apply(room);
      return ResponseEntity.status(HttpStatus.CREATED).body(filteredRoom);
    } catch (ExceptionBuilder e) {
      response = responseMessageHandler.generateResponseMessageJson(e.getMessage());
      return ResponseEntity.status(e.getHttpStatus()).body(response);
    }
  }

  @PutMapping(path = "/update/{roomId}")
  public ResponseEntity<Map<String, String>> updateRoom(@PathVariable("roomId") @Min(1) int roomId,
                                                        @RequestParam(required = true) @Min(1) int userId,
                                                        @RequestParam(required = false) String sprintName,
                                                        @RequestParam(required = false) String teamName,
                                                        @RequestParam(required = false) String description,
                                                        @RequestParam(required = false) @Min(1) Integer deckId,
                                                        @RequestParam(required = false) List<Integer> participantIdList) {
    try {
      roomService.updateRoom(
          roomId,
          userId,
          sprintName,
          teamName,
          description,
          deckId,
          participantIdList
      );
      response = responseMessageHandler.generateResponseMessageJson("Room updated successfully.");
      return ResponseEntity.ok().body(response);

    } catch (ExceptionBuilder e) {
      response = responseMessageHandler.generateResponseMessageJson(e.getMessage());
      return ResponseEntity.status(e.getHttpStatus()).body(response);
    }
  }

  @DeleteMapping(path = "/delete/{roomId}")
  public ResponseEntity<Map<String, String>> deleteRoom(@PathVariable("roomId") @Min(1) int roomId,
                                                        @RequestParam(required = true) @Min(1) int userId) {
    try {
      roomService.deleteRoom(roomId, userId);
      response = responseMessageHandler.generateResponseMessageJson("Room deleted successfully.");
      return ResponseEntity.ok(response);
    } catch (ExceptionBuilder e) {
      response = responseMessageHandler.generateResponseMessageJson(e.getMessage());
      return ResponseEntity.status(e.getHttpStatus()).body(response);
    }
  }

}

