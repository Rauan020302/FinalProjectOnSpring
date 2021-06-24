package it.academy.project.projectonspring.controller;

import it.academy.project.projectonspring.entity.User;
import it.academy.project.projectonspring.exception.AuthorizationException;
import it.academy.project.projectonspring.model.AuthModel;
import it.academy.project.projectonspring.model.SignUpModel;
import it.academy.project.projectonspring.model.UserModel;
import it.academy.project.projectonspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/sign-up")
    public User saveUser (@RequestBody UserModel userModel) throws AuthorizationException {
        return userService.saveUser(userModel);
    }
    @PostMapping("sign-up-model")
    public User saveUser (@RequestBody SignUpModel signUpModel) throws AuthorizationException {
        return userService.saveUser(signUpModel);
    }

    @PostMapping("/sign-in")
    public String getToken(@RequestBody AuthModel authModel) {
        return userService.getTokenByAuthModel(authModel);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }
    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable Long id){
        return userService.deleteUserById(id);
    }
    @PutMapping("/{id}")
    public User updateUser(@RequestBody UserModel userModel, @PathVariable Long id) throws AuthorizationException {
        return userService.updateUserById(userModel,id);
    }
}

