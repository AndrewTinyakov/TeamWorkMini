package SpaceProg.teamwork.service;

import SpaceProg.teamwork.dao.UserDao;
import SpaceProg.teamwork.exeption.notFoundExeption.UserNotFoundException;
import SpaceProg.teamwork.model.User;
import SpaceProg.teamwork.model.page.ColleaguesPage;
import SpaceProg.teamwork.payload.request.upgrateRequest.UpdateUserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class UserServiceSimple implements UserService {
    private final UserDao dao;
    private final PasswordEncoder passwordEncoder;


    public UserServiceSimple(UserDao dao, PasswordEncoder passwordEncoder) {
        this.dao = dao;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public User findById(Long id) throws UserNotFoundException {
        User user = dao.findById(id).orElseThrow(UserNotFoundException::new);

        log.info("User has been found");

        return user;

    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode((user.getPassword())));
        dao.save(user);
        log.info("Saved user in the database");
    }

    @Override
    public User updateUser(UpdateUserRequest newUser, Long id) throws UserNotFoundException {
        User user = this.findById(id);
        user.setUsername(newUser.getUsername());
        user.setEmail(newUser.getEmail());
        user.setName(newUser.getName());
        user.setSurname(newUser.getSurname());
//TODO model mapper
        dao.save((user));
        log.info("Updated user in the database");

        return user;
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
        log.info("User was deleted from the database");
    }

    @Override
    public ColleaguesPage findUserColleagues(Long id, Pageable pageable) throws UserNotFoundException {
        Page<User> page1 = dao.findUserFirstColleaguesById(pageable, id);
        Page<User> page2 = dao.findUserSecondColleaguesBuId(pageable, id);

        List<User> page = new ArrayList<>();
        page.addAll(page1.getContent());
        page.addAll(page2.getContent());

        int totalPages = page1.getTotalPages() + page2.getTotalPages();

        return new ColleaguesPage(
                page,
                pageable.getPageNumber(),
                totalPages
        );
    }

    public Optional<User> findUserByUsername(String name) {
        Optional<User> user = dao.findByUsername(name);
        log.info("Found user by username");
        return user;
    }

    @Override
    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = findUserByUsername(auth.getName()).orElse(null);

        log.info("Got current user");
        return user;
    }

    @Override
    public Boolean existsByUsername(String username) {
        return dao.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return dao.existsByEmail(email);
    }


    public void addColleague(Long id) throws UserNotFoundException {
        User user = getCurrentUser();
        User colleague = findById(id);
        user.getColleaguesFirst().add(colleague);
        saveUser(user);
    }


}
