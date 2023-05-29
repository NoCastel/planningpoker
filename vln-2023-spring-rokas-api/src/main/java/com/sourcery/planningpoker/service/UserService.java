package com.sourcery.planningpoker.service;

import com.sourcery.planningpoker.dto.UserDto;
import com.sourcery.planningpoker.dto.mapper.UserDtoMapper;
import com.sourcery.planningpoker.exceptions.ExceptionBuilder;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.sourcery.planningpoker.interfaces.service.UserServiceInterface;
import com.sourcery.planningpoker.mapper.UserMapper;
import com.sourcery.planningpoker.model.User;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserServiceInterface {

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private UserDtoMapper userDtoMapper;

  @Override
  public List<UserDto> getAllUsers() {
    List<User> users = userMapper.getAllUsers();
    return users.stream().map(userDtoMapper).collect(Collectors.toList());
  }

  @Override
  public UserDto getUserById(int userId) {
    return userMapper.getUserById(userId).map(userDtoMapper).orElse(null);
  }

  @Override
  public UserDto getUserByEmailAndPassword(String email, String password) {
    return userMapper.getUserByEmailAndPassword(email, password).map(userDtoMapper).orElse(null);
  }

  @Override
  public UserDto addUser(User user) throws ExceptionBuilder {
    Optional<User> userById = userMapper.getUserById(user.getUserId());
    Optional<User> userByUserName = userMapper.getUserByUserName(user.getUserName());
    Optional<User> userByEmail = userMapper.getUserByEmail(user.getEmail());

    if (userByUserName.isPresent()) {
      throw new ExceptionBuilder("User name is already taken.", HttpStatus.CONFLICT);
    }

    if (userByEmail.isPresent()) {
      throw new ExceptionBuilder("Email already taken.", HttpStatus.CONFLICT);
    }
    userMapper.addUser(user);
    return userDtoMapper.apply(user);
  }

  @Transactional
  public void updateUser(int userId, String userName, String email, String password) throws ExceptionBuilder {

    Optional<User> user = userMapper.getUserById(userId);
    if (user.isEmpty()) {
      throw new ExceptionBuilder("User not found.", HttpStatus.NOT_FOUND);
    }

    if (userName != null
        && !userName.trim().isEmpty()
        && !Objects.equals(user.get().getUserName(), userName)) {
      Optional<User> userByUserName = userMapper.getUserByUserName(userName);
      if (userByUserName.isPresent()) {
        throw new ExceptionBuilder(String.format("User name: %s is already taken.", userName), HttpStatus.CONFLICT);
      } else {
        user.get().setUserName(userName);
      }
    }

    if (email != null
        && !email.trim().isEmpty()
        && !Objects.equals(user.get().getEmail(), email)) {
      Optional<User> userByEmail = userMapper.getUserByEmail(email);
      if (userByEmail.isPresent()) {
        throw new ExceptionBuilder(String.format("Email: %s is already taken.", email), HttpStatus.CONFLICT);
      } else {
        user.get().setEmail(email);
      }
    }

    if (password != null && !password.trim().isEmpty()) {
      if (Objects.equals(user.get().getPassword(), password)) {
        throw new ExceptionBuilder("New password must differ from the old password.", HttpStatus.CONFLICT);
      } else {
        user.get().setPassword(password);
      }
    }
    userMapper.updateUser(user.get());
  }

  public User deleteUser(int userId) {
    Optional<User> user = userMapper.getUserById(userId);
    if (user.isEmpty()) {
      return null;
    }
    userMapper.deleteUser(userId);
    return user.get();
  }
}
