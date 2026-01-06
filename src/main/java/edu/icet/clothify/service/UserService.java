package edu.icet.clothify.service;

import edu.icet.clothify.model.dto.UserDTO;

public interface UserService {
    boolean addUser(UserDTO userDTO);
    UserDTO getUser(String email);
}
