package SpaceProg.teamwork.service;

import SpaceProg.teamwork.model.User;

public interface AuthenticationService {
    void registration(User user);

    boolean findUserToResetPassword(String login, String email);

//    void resetPassword(String login, String password);
}
