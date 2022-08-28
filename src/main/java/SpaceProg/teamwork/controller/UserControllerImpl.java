package SpaceProg.teamwork.controller;

import SpaceProg.teamwork.exeption.SentInviteException;
import SpaceProg.teamwork.exeption.notFoundExeption.UserNotFoundException;
import SpaceProg.teamwork.model.ColleaguesInvite;
import SpaceProg.teamwork.model.User;
import SpaceProg.teamwork.model.page.ColleaguesInvitePage;
import SpaceProg.teamwork.model.page.ColleaguesPage;
import SpaceProg.teamwork.model.page.PostPage;
import SpaceProg.teamwork.payload.request.upgrateRequest.UpdateUserRequest;
import SpaceProg.teamwork.payload.response.LinkToUser;
import SpaceProg.teamwork.payload.response.UserResponse;
import SpaceProg.teamwork.payload.response.pageResponse.ColleaguesInvitePageResponse;
import SpaceProg.teamwork.payload.response.pageResponse.ColleaguesPageResponse;
import SpaceProg.teamwork.payload.response.pageResponse.PostPageResponse;
import SpaceProg.teamwork.service.PostService;
import SpaceProg.teamwork.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
class UserControllerImpl implements UserController {

    private final UserService userService;
    private final PostService postService;
    private final ModelMapper modelMapper;

    public UserControllerImpl(UserService userService, PostService postService, ModelMapper modelMapper) {
        this.userService = userService;
        this.postService = postService;
        this.modelMapper = modelMapper;

    }

    @GetMapping("/{id}")
    public UserResponse findUser(@PathVariable Long id) throws UserNotFoundException {
        User user = userService.findById(id);
        return covertUserToResponse(user);
    }

    @PutMapping()
    public User updateUser(@RequestBody UpdateUserRequest newUser) throws UserNotFoundException {
        return userService.updateUser(newUser, userService.getCurrentUser().getId());
    }

    @DeleteMapping()
    public void deleteUser() {
        userService.deleteById(userService.getCurrentUser().getId());
    }

    //TODO Users friends


    @GetMapping("/{id}/posts")
    public PostPageResponse getUserPosts(@PathVariable Long id,
                                         @PageableDefault(size = 10, sort = {"sendingTime"}, direction = Sort.Direction.DESC)
                                                 Pageable pageable) throws UserNotFoundException {
        PostPage page = postService.findUsersPost(id, pageable);
        return convertPostsToPageResponse(page);
    }

    @GetMapping("/{id}/colleagues")
    public ColleaguesPageResponse getUsersColleagues(@PathVariable Long id,
                                                     @PageableDefault(size = 5)
                                                             Pageable pageable) throws UserNotFoundException {
        ColleaguesPage page = userService.findUserColleagues(id, pageable);
        return convertUsersToPageResponse(page);

    }

    @PutMapping("/colleague/{id}")
    public void sendColleagueInvite(@PathVariable Long id) throws UserNotFoundException, SentInviteException {
        userService.sendColleagueInvite(id);
    }

    @GetMapping("/{id}/colleaguesInvite")
    public ColleaguesInvitePageResponse getUserColleaguesInvite(@PathVariable Long id,
                                                                 @PageableDefault(size = 5)
                                                                         Pageable pageable) throws UserNotFoundException {
        ColleaguesInvitePage colleaguesInvitePage = userService.findUserColleaguesInvite(id, pageable);
        return  covertInvitesToResponse(colleaguesInvitePage);
    }

    @PutMapping("/acceptColleagueInvite/{id}")
    public void acceptRequest(@PathVariable Long id) throws Exception {
        userService.addColleagueFromInvite(id);
    }

    @DeleteMapping("/colleague/{id}")
    public void deleteFromFriend(@PathVariable Long id){
        userService.deleteColleageById(id);
    }

    public UserResponse covertUserToResponse(User user) {
        return modelMapper.map(user, UserResponse.class);
    }

    public ColleaguesPageResponse convertUsersToPageResponse(ColleaguesPage page) {
        return modelMapper.map(page, ColleaguesPageResponse.class);
    }

    public PostPageResponse convertPostsToPageResponse(PostPage page) {
        return modelMapper.map(page, PostPageResponse.class);
    }

    private ColleaguesInvitePageResponse covertInvitesToResponse(ColleaguesInvitePage colleaguesInvitePage) {


        return modelMapper.map(colleaguesInvitePage, ColleaguesInvitePageResponse.class);
    }
}
