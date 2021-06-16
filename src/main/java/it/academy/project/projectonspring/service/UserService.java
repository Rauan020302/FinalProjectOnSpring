package it.academy.project.projectonspring.service;

import it.academy.project.projectonspring.entity.User;
import it.academy.project.projectonspring.exception.ObjectsNotFoundException;
import it.academy.project.projectonspring.model.AuthModel;
import it.academy.project.projectonspring.model.UserModel;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id) throws ObjectsNotFoundException;
    User saveUser(User user) throws ObjectsNotFoundException;
    User saveUser(UserModel userModel) throws ObjectsNotFoundException;
    User deleteUserById(Long id) throws ObjectsNotFoundException;
    User updateUserById(UserModel userModel,Long id) throws ObjectsNotFoundException;
    User findByUsername(String login) throws ObjectsNotFoundException;
    String getTokenByAuthModel(AuthModel authModel) throws ObjectsNotFoundException;
    List<User> findAllByStatus(Long status);
}
