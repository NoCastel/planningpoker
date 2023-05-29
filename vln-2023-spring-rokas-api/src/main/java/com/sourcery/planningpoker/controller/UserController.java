package com.sourcery.planningpoker.controller;

import java.util.List;
import java.util.Map;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
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

import com.sourcery.planningpoker.dto.UserDto;
import com.sourcery.planningpoker.exceptions.ExceptionBuilder;
import com.sourcery.planningpoker.handlers.response.ResponseMessageHandler;
import com.sourcery.planningpoker.model.User;
import com.sourcery.planningpoker.service.UserService;

@RestController
@Validated
@CrossOrigin
@RequestMapping("api/v1/users")
public class UserController {

  //TODO error when /allusers vs /allUsers
  @Autowired
  private UserService userService;

  @Autowired
  private ResponseMessageHandler responseMessageHandler;

  Map<String, String> response;

  @GetMapping("/all-users")
  public ResponseEntity<List<UserDto>> getAllUsers() {
    List<UserDto> users = userService.getAllUsers();
    return users.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(users);
  }

  @GetMapping("/{userId}")
  public ResponseEntity<UserDto> getUserById(@PathVariable("userId") @Min(1) int userId) {
    UserDto user = userService.getUserById(userId);
    return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
  }

  @GetMapping("/login")
  public ResponseEntity<Object> getUserByEmailAndPassword(@RequestParam @Email String email,
                                                          @RequestParam @NotEmpty String password) {
    UserDto user = userService.getUserByEmailAndPassword(email, password);
    if (user != null) {
      return ResponseEntity.ok(user);
    } else {
      Map<String, String> response = responseMessageHandler.generateResponseMessageJson(
          "Email or password is incorrect.");
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
  }

  @PostMapping("/add")
  public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
    try {
      UserDto newUser = userService.addUser(user);
      return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    } catch (ExceptionBuilder e) {
      response = responseMessageHandler.generateResponseMessageJson(e.getMessage());
      return ResponseEntity.status(e.getHttpStatus()).body(response);
    }
  }

  @PutMapping(path = "/update/{id}")
  public ResponseEntity<Map<String, String>> updateUser(@PathVariable("id") @Min(1) int id,
                                                        @RequestParam(required = false) String userName,
                                                        @RequestParam(required = false) @Email String email,
                                                        @RequestParam(required = false) String password) {
    try {
      userService.updateUser(id, userName, email, password);
      response = responseMessageHandler.generateResponseMessageJson("User updated successfully.");
      return ResponseEntity.ok(response);
    } catch (ExceptionBuilder e) {
      response = responseMessageHandler.generateResponseMessageJson(e.getMessage());
      return ResponseEntity.status(e.getHttpStatus()).body(response);
    }
  }

  @DeleteMapping(path = "/delete/{userId}")
  public ResponseEntity<Object> deleteUser(@PathVariable("userId") @Min(1) int userId) {
    User user = userService.deleteUser(userId);
    if (user != null) {
      response = responseMessageHandler.generateResponseMessageJson("User deleted successfully.");
      return ResponseEntity.ok(response);
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
