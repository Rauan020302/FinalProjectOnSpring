package it.academy.project.projectonspring.service;

import it.academy.project.projectonspring.entity.UserRole;
import it.academy.project.projectonspring.exception.ObjectsNotFoundException;
import it.academy.project.projectonspring.model.UserRoleModel;

import java.util.List;

public interface UserRoleService {
    List<UserRole> getAllUserRoles();
    UserRole getRoleById(Long id) throws ObjectsNotFoundException;
    UserRole deleteRoleById(Long id) throws ObjectsNotFoundException;
    UserRole updateRoleById(UserRoleModel userRoleModel, Long id) throws ObjectsNotFoundException;
    UserRole saveRole(UserRole userRole);
    UserRole saveRole(UserRoleModel userRoleModel) throws IllegalAccessException, ObjectsNotFoundException;
    List<UserRole> findAllByUser_Id(Long id);
    UserRole findByUser_Id(Long id);

}
