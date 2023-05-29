package com.sourcery.planningpoker.dto.mapper;

import com.sourcery.planningpoker.dto.UserDto;
import com.sourcery.planningpoker.model.User;
import org.springframework.stereotype.Service;
import java.util.function.Function;

@Service
public class UserDtoMapper implements Function<User, UserDto> {

  @Override
  public UserDto apply(User user) {
    return new UserDto(
        user.getUserId(),
        user.getUserName(),
        user.getEmail()
    );
  }
}
