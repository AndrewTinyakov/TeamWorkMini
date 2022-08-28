package SpaceProg.teamwork.service;

import SpaceProg.teamwork.exeption.SentInviteException;
import SpaceProg.teamwork.exeption.notFoundExeption.UserNotFoundException;
import SpaceProg.teamwork.model.User;
import SpaceProg.teamwork.model.page.ColleaguesInvitePage;
import SpaceProg.teamwork.payload.request.upgrateRequest.UpdateUserRequest;
import SpaceProg.teamwork.model.page.ColleaguesPage;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    User findById(Long id) throws UserNotFoundException;

    void saveUser(User user);

    public void register(User user);

    User updateUser(UpdateUserRequest newUser, Long id) throws UserNotFoundException;

    void deleteById(Long id);

    User getCurrentUser();

    Optional<User> findUserByUsername(String name);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    ColleaguesPage findUserColleagues(Long id, Pageable pageable) throws UserNotFoundException;

    public void addColleagueFromInvite(Long id) throws Exception;

    void sendColleagueInvite(Long id) throws UserNotFoundException, SentInviteException;

    ColleaguesInvitePage findUserColleaguesInvite(Long id, Pageable pageable) throws UserNotFoundException;

    void deleteColleageById(Long id);
}
