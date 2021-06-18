package it.academy.project.projectonspring.service;

import it.academy.project.projectonspring.entity.UserRole;
import it.academy.project.projectonspring.model.UserRoleModel;

import java.util.List;

public interface UserRoleService {
    List<UserRole> getAllUserRoles();
    UserRole getRoleById(Long id);
    UserRole deleteRoleById(Long id);
    UserRole updateRoleById(UserRoleModel userRoleModel, Long id);
    UserRole saveRole(UserRole userRole);
    UserRole saveRole(UserRoleModel userRoleModel);
    List<UserRole> findAllByUser_Id(Long id);
    UserRole findByUser_Id(Long id);

}
