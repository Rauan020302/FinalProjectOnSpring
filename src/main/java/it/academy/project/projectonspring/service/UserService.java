package it.academy.project.projectonspring.service;

import it.academy.project.projectonspring.entity.User;
import it.academy.project.projectonspring.exception.AuthorizationException;
import it.academy.project.projectonspring.model.AuthModel;
import it.academy.project.projectonspring.model.SignUpModel;
import it.academy.project.projectonspring.model.UserModel;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User saveUser(User user) throws AuthorizationException;
    User saveUser(UserModel userModel) throws AuthorizationException;
    User saveUser(SignUpModel signUpModel) throws AuthorizationException;
    User deleteUserById(Long id);
    User updateUserById(UserModel userModel,Long id) throws AuthorizationException;
    User findByUsername(String login);
    AuthModel getTokenByAuthModel(AuthModel authModel);
    List<User> findAllByStatus(Long status);
}
