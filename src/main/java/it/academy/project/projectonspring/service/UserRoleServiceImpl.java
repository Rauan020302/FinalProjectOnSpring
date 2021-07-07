package it.academy.project.projectonspring.service;

import it.academy.project.projectonspring.entity.User;
import it.academy.project.projectonspring.entity.UserRole;
import it.academy.project.projectonspring.exception.ObjectsNotFoundException;
import it.academy.project.projectonspring.model.UserRoleModel;
import it.academy.project.projectonspring.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private UserService userService;

    @Override
    public UserRole saveRole(UserRoleModel userRoleModel){
        User user = userService.getUserById(userRoleModel.getUserId());
        try {
            if (user == null)throw new ObjectsNotFoundException();
            UserRole userRole = UserRole.builder()
                    .roleName(userRoleModel.getRoleName())
                    .user(user).build();
            return saveRole(userRole);
        }catch (ObjectsNotFoundException e){
            throw new ObjectsNotFoundException("user not found by id - " + Objects.requireNonNull(user).getId());
        }
    }

    @Override
    public UserRole deleteRoleById(Long id){
        UserRole userRole = getRoleById(id);
        if (userRole != null){
            userRoleRepository.delete(userRole);
            return userRole;
        }
        return null;
    }

    @Override
    public UserRole updateRoleById(UserRoleModel userRoleModel, Long id){
        User user = userService.getUserById(userRoleModel.getUserId());
        try {
            if (user == null) throw new ObjectsNotFoundException();
            UserRole newUserRole = getRoleById(id);
            newUserRole.setRoleName(userRoleModel.getRoleName());
            newUserRole.setUser(user);
            return saveRole(newUserRole);
        }catch (ObjectsNotFoundException e){
            throw new ObjectsNotFoundException("not found userRole by id - " + id);
        }
    }
    @Override
    public UserRole getRoleById(Long id){
        return userRoleRepository.findById(id)
                .orElseThrow(() -> new ObjectsNotFoundException("not found userRole by id - " + id));
    }
    @Override
    public List<UserRole> findAllByUser_Id(Long id) {
        return userRoleRepository.findAllByUser_Id(id);
    }

    @Override
    public UserRole findByUser_Id(Long id) {
        return userRoleRepository.findByUser_Id(id);
    }

    @Override
    public UserRole saveRole(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    @Override
    public List<UserRole> getAllUserRoles() {
        return userRoleRepository.findAll();
    }
}

