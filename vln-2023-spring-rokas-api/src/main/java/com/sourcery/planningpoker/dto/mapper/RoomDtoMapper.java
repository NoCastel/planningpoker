package com.sourcery.planningpoker.dto.mapper;

import com.sourcery.planningpoker.dto.RoomDto;
import com.sourcery.planningpoker.model.Room;
import org.springframework.stereotype.Service;
import java.util.function.Function;

@Service
public class RoomDtoMapper implements Function<Room, RoomDto> {

  @Override
  public RoomDto apply(Room room) {
    return new RoomDto(
        room.getRoomId(),
        room.getRoomName(),
        room.getTeamName(),
        room.getSprintName(),
        room.getDescription(),
        room.getActive(),
        room.getCreatorId(),
        room.getDeckId()
    );
  }
}


