package edu.icet.clothify.service.impl;
import org.mindrot.jbcrypt.BCrypt;
import edu.icet.clothify.model.dto.UserDTO;
import edu.icet.clothify.model.entity.User;
import edu.icet.clothify.repository.UserRepository;
import edu.icet.clothify.repository.impl.UserRepositoryImpl;
import edu.icet.clothify.service.UserService;

public class UserServiceImpl implements UserService {
    UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public boolean addUser(UserDTO userDTO) {
        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setEmail(userDTO.getEmail());
        newUser.setPassword(getHashedPassword(userDTO.getPassword()));
        newUser.setRole(checkRole(userDTO.getEmail()));
        newUser.setImageUrl(userDTO.getImage());
        return userRepository.addUser(newUser);
    }

    @Override
    public UserDTO getUser(String email) {
        User user = userRepository.getUser(email);
        return new UserDTO(user.getUsername(),user.getEmail(),user.getPassword(),user.getImageUrl());
    }

    private String checkRole(String email) {
        return email.endsWith("@clothify.com") ? "ADMIN" : "USER";
    }
    private String getHashedPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

}
