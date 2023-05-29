package com.sourcery.planningpoker.controller;

import com.sourcery.planningpoker.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sourcery.planningpoker.service.RoleService;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/roles")
public class RoleController {
  @Autowired
  private RoleService roleService;

  @GetMapping("/all-roles")
  public ResponseEntity<List<Role>> getAllRoles() {
    List<Role> roles = roleService.getallRoles();
    return roles.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(roles);
  }
}
