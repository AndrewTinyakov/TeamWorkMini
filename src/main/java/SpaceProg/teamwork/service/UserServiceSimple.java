package SpaceProg.teamwork.service;

import SpaceProg.teamwork.dao.ColleaguesInviteDao;
import SpaceProg.teamwork.dao.UserDao;
import SpaceProg.teamwork.exeption.SentInviteException;
import SpaceProg.teamwork.exeption.notFoundExeption.UserNotFoundException;
import SpaceProg.teamwork.model.ColleaguesInvite;
import SpaceProg.teamwork.model.User;
import SpaceProg.teamwork.model.page.ColleaguesInvitePage;
import SpaceProg.teamwork.model.page.ColleaguesPage;
import SpaceProg.teamwork.payload.request.upgrateRequest.UpdateUserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Slf4j
@Transactional
public class UserServiceSimple implements UserService {
    private final UserDao userDao;
    private final ColleaguesInviteDao colleaguesInviteDao;
    private final PasswordEncoder passwordEncoder;

    public UserServiceSimple(UserDao userDao, ColleaguesInviteDao colleaguesInviteDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.colleaguesInviteDao = colleaguesInviteDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findById(Long id) throws UserNotFoundException {
        User user = userDao.findById(id).orElseThrow(UserNotFoundException::new);

        log.info("User has been found");

        return user;

    }

    @Override
    public void saveUser(User user) {
        userDao.save(user);
        log.info("Saved user in the database");
    }

    public void register(User user){
        user.setPassword(passwordEncoder.encode((user.getPassword())));
        userDao.save(user);
        log.info("Registered user");
    }

    @Override
    public User updateUser(UpdateUserRequest newUser, Long id) throws UserNotFoundException {
        User user = this.findById(id);
        user.setUsername(newUser.getUsername());
        user.setEmail(newUser.getEmail());
        user.setName(newUser.getName());
        user.setSurname(newUser.getSurname());
//TODO model mapper
        userDao.save((user));
        log.info("Updated user in the database");

        return user;
    }

    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
        log.info("User was deleted from the database");
    }

    public Optional<User> findUserByUsername(String name) {
        Optional<User> user = userDao.findByUsername(name);
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
    public ColleaguesPage findUserColleagues(Long id, Pageable pageable) throws UserNotFoundException {
        Page<User> page1 = userDao.findUserFirstColleaguesById(pageable, id);
        Page<User> page2 = userDao.findUserSecondColleaguesBuId(pageable, id);

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

    @Override
    public ColleaguesInvitePage findUserColleaguesInvite(Long id, Pageable pageable) throws UserNotFoundException {
        User user = findById(id);
        Set<ColleaguesInvite> colleaguesInviteSet = user.getColleaguesInvites();
        List<ColleaguesInvite> colleaguesInvites = new ArrayList<>(colleaguesInviteSet);

        Page<ColleaguesInvite> page = new PageImpl<>(colleaguesInvites);

        return new ColleaguesInvitePage(
                page.getContent(),
                pageable.getPageNumber(),
                page.getTotalPages()
        );
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userDao.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userDao.existsByEmail(email);
    }


    public void addColleagueFromInvite(Long id) throws Exception {
        User user = getCurrentUser();
        ColleaguesInvite colleaguesInvite = colleaguesInviteDao.findById(id).orElseThrow((Exception::new));
        User colleague = colleaguesInvite.getSender();
        user.getColleaguesFirst().add(colleague);
        saveUser(user);
        colleaguesInviteDao.deleteById(id);
    }

    @Override
    public void sendColleagueInvite(Long id) throws UserNotFoundException, SentInviteException {
        User current = getCurrentUser();
        User recipient = findById(id);

        Set<ColleaguesInvite> colleaguesInvites =  recipient.getColleaguesInvites();
        List<ColleaguesInvite> colleaguesInviteList = new ArrayList<>(colleaguesInvites);
        for (ColleaguesInvite invite : colleaguesInviteList) {
            if (invite.getRecipient().getId().equals(id)) {
                throw new SentInviteException();
            }
        }

        Set<User> colleaguesFirst = recipient.getColleaguesFirst();
        Set<User> colleaguesSecond = recipient.getColleaguesSecond();

        Set<User> colleagues = new HashSet<>();
        colleagues.addAll(colleaguesFirst);
        colleagues.addAll(colleaguesSecond);

        List<User> colleaguesList = new ArrayList<>(colleagues);

        for (User user : colleaguesList) {
            if (user.getId().equals(id)) {
                throw new SentInviteException();
            }
        }

        ColleaguesInvite colleaguesInvite = new ColleaguesInvite(
                getCurrentUser(),
                findById(id)
        );
        colleaguesInviteDao.save(colleaguesInvite);
    }

    @Override
    public void deleteColleageById(Long id) {

    }
}
