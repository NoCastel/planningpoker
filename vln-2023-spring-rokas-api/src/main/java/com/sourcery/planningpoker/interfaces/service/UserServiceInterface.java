package com.sourcery.planningpoker.interfaces.service;

import com.sourcery.planningpoker.dto.UserDto;
import com.sourcery.planningpoker.exceptions.ExceptionBuilder;

import com.sourcery.planningpoker.model.User;
import java.util.List;

public interface UserServiceInterface {

  List<UserDto> getAllUsers();

  UserDto getUserById(int userId);

  UserDto getUserByEmailAndPassword(String email, String password);

  UserDto addUser(User user) throws ExceptionBuilder;

  void updateUser(int userId, String user_name, String email, String password) throws ExceptionBuilder;

  User deleteUser(int userId);

}
