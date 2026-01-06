package edu.icet.clothify.config;

import edu.icet.clothify.model.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserSession {
    private static UserSession instance;
    private UserDTO loggedUser;

    private UserSession() {}

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }
    public void cleanUserSession() {
        loggedUser = null;
    }
}
