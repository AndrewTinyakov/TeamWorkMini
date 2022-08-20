package SpaceProg.teamwork.service;

import SpaceProg.teamwork.model.User;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceBasic implements AuthenticationService {
    private final UserService userService;


    public AuthenticationServiceBasic(UserService userService) {
        this.userService = userService;
    }

    public void registration(User user) {
        userService.saveUser(user);
    }

    public boolean findUserToResetPassword(String login, String email) {


        //if it's u

        return true;
    }


//    public void resetPassword(String login, String password) {
//        User user = dao.findByLogin(login);
//        user.setPassword(password);
//        userService.updateUser(user);
//    }
}
