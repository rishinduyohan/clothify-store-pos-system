package edu.icet.clothify.repository;

import edu.icet.clothify.model.entity.User;

public interface UserRepository {
    boolean addUser(User user);
    User getUser(String email);
}
