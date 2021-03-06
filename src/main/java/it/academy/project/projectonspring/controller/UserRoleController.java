package it.academy.project.projectonspring.controller;

import it.academy.project.projectonspring.entity.UserRole;
import it.academy.project.projectonspring.model.UserRoleModel;
import it.academy.project.projectonspring.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user_role")
public class UserRoleController {
    @Autowired
    private UserRoleService userRoleService;

    @PostMapping
    public UserRole saveUserRole(@RequestBody UserRoleModel userRoleModel){
        return userRoleService.saveRole(userRoleModel);
    }
    @GetMapping
    public List<UserRole> getAllUserRoles(){
        return userRoleService.getAllUserRoles();
    }
    @GetMapping("/users/{id}")
    public List<UserRole> getUserRoleByUsersId(@PathVariable Long id){
        return userRoleService.findAllByUser_Id(id);
    }
    @GetMapping("/{id}")
    public UserRole getUserRoleById(@PathVariable Long id){
        return userRoleService.getRoleById(id);
    }
    @DeleteMapping("/{id}")
    public UserRole deleteUserRoleById(@PathVariable Long id){
        return userRoleService.deleteRoleById(id);
    }
    @PutMapping("/{id}")
    public UserRole updateUserRoleById(@RequestBody UserRoleModel userRoleModel,
                                       @PathVariable Long id){
        return userRoleService.updateRoleById(userRoleModel,id);
    }
}
