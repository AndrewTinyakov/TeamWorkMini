package SpaceProg.teamwork.service;

import SpaceProg.teamwork.exeption.notFoundExeption.UserNotFoundException;
import SpaceProg.teamwork.model.User;
import SpaceProg.teamwork.payload.request.upgrateRequest.UpdateUserRequest;
import SpaceProg.teamwork.model.page.ColleaguesPage;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    User findById(Long id) throws UserNotFoundException;

    void saveUser(User user);

    User updateUser(UpdateUserRequest newUser, Long id) throws UserNotFoundException;

    void deleteById(Long id);

    User getCurrentUser();

    Optional<User> findUserByUsername(String name);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    ColleaguesPage findUserColleagues(Long id, Pageable pageable) throws UserNotFoundException;

    public void addColleague(Long id) throws UserNotFoundException;
}
