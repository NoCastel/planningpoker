package com.sourcery.planningpoker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sourcery.planningpoker.interfaces.service.RoleServiceInterface;
import com.sourcery.planningpoker.mapper.RoleMapper;
import com.sourcery.planningpoker.model.Role;

import java.util.List;

@Service
public class RoleService implements RoleServiceInterface {

  @Autowired
  RoleMapper roleMapper;

  @Override
  public List<Role> getallRoles() {
    return roleMapper.getAllRoles();
  }
}