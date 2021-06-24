package it.academy.project.projectonspring.service;

import it.academy.project.projectonspring.entity.Image;
import it.academy.project.projectonspring.entity.User;
import it.academy.project.projectonspring.entity.UserRole;
import it.academy.project.projectonspring.exception.AuthorizationException;
import it.academy.project.projectonspring.exception.ObjectsNotFoundException;
import it.academy.project.projectonspring.model.AuthModel;
import it.academy.project.projectonspring.model.SignUpModel;
import it.academy.project.projectonspring.model.UserModel;
import it.academy.project.projectonspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ImageService imageService;
    @Autowired
    private UserRoleService userRoleService;

    @Override
    public User saveUser(User user) throws AuthorizationException {
        try {
            if (user.getUsername().equals("") || user.getPassword().equals("")
                    || user.getUsername() == null || user.getPassword() == null) {
                throw new AuthorizationException();
            }
            return saveUserWithRole(user);
        }catch (AuthorizationException e){
            throw new AuthorizationException("username or password is not correct");
        }
    }

    @Override
    public User saveUser(UserModel userModel) throws AuthorizationException {
        try {
            if (userModel.getUsername().equals("") || userModel.getPassword().equals("")
                    || userModel.getUsername() == null || userModel.getPassword() == null) {
                throw new AuthorizationException();
            }
            Image image = imageService.getImageById(userModel.getImageId());
            User user = User.builder()
                    .username(userModel.getUsername())
                    .status(1L)
                    .profession(userModel.getProfession())
                    .password(userModel.getPassword())
                    .image(image)
                    .fullName(userModel.getFullName())
                    .contact(userModel.getContact())
                    .address(userModel.getAddress())
                    .createdDate(userModel.getCreatedDate())
                    .build();

            return saveUserWithRole(user);
        }catch (AuthorizationException e){
            throw new AuthorizationException("username or password is not correct");
        }
    }

    @Override
    public User saveUser(SignUpModel signUpModel) throws AuthorizationException {
        try {
            if (signUpModel.getUsername().equals("") || signUpModel.getPassword().equals("")
                    || signUpModel.getUsername() == null || signUpModel.getPassword() == null) {
                throw new AuthorizationException();
            }
            User user = User.builder()
                    .username(signUpModel.getUsername())
                    .password(signUpModel.getPassword())
                    .fullName(signUpModel.getFullName()).build();
            return saveUserWithRole(user);
        } catch (AuthorizationException e) {
            throw new AuthorizationException("username or password is not correct");
        }
    }

        private User saveUserWithRole(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        UserRole userRole = new UserRole();
        userRole.setRoleName("ROLE_ADMIN");
        userRole.setUser(user);
        userRoleService.saveRole(userRole);
        return user;
    }

    @Override
    public User deleteUserById(Long id){
        User user = getUserById(id);
        if (user != null){
            userRepository.delete(user);
            return user;
        }
        return null;
    }

    @Override
    public User updateUserById(UserModel userModel, Long id) throws AuthorizationException {
        User newUser = getUserById(id);
        Image image = imageService.getImageById(userModel.getImageId());
        if (newUser == null) throw new ObjectsNotFoundException();

        newUser.setUsername(userModel.getUsername());
        newUser.setFullName(userModel.getFullName());
        newUser.setPassword(userModel.getPassword());
        newUser.setProfession(userModel.getProfession());
        newUser.setStatus(userModel.getStatus());
        newUser.setAddress(userModel.getAddress());
        newUser.setContact(userModel.getContact());
        newUser.setImage(image);
        return saveUser(newUser);
    }

    @Override
    public User getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new ObjectsNotFoundException("not found user by id - " + id));
    }



    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String login){
        return userRepository.findByUsername(login)
                .orElseThrow(() -> new ObjectsNotFoundException("not found user by username " + login));
    }

    @Override
    public String getTokenByAuthModel(AuthModel authModel){
        String authResult = "";
        User user = findByUsername(authModel.getUsername());
        if (user == null) authResult = "Неверный логин/пароль";
        else{
            if (passwordEncoder.matches(authModel.getPassword(),user.getPassword())){
                String loginPassPair = user.getUsername() + ":" + authModel.getPassword();
                authResult = "Basic " + Base64.getEncoder()
                        .encodeToString(loginPassPair.getBytes());
            }else authResult = "Неверный логин/пароль";
        }

        return authResult;
    }

    @Override
    public List<User> findAllByStatus(Long status) {
        return userRepository.findAllByStatus(status);
    }
}

