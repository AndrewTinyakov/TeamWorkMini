package SpaceProg.teamwork.controller;

import SpaceProg.teamwork.exeption.SentInviteException;
import SpaceProg.teamwork.exeption.notFoundExeption.UserNotFoundException;
import SpaceProg.teamwork.model.User;
import SpaceProg.teamwork.model.page.ColleaguesPage;
import SpaceProg.teamwork.model.page.PostPage;
import SpaceProg.teamwork.payload.request.upgrateRequest.UpdateUserRequest;
import SpaceProg.teamwork.payload.response.pageResponse.ColleaguesPageResponse;
import SpaceProg.teamwork.payload.response.pageResponse.PostPageResponse;
import SpaceProg.teamwork.payload.response.UserResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserController {

    public UserResponse findUser(@PathVariable Long id) throws UserNotFoundException;

    public User updateUser(@RequestBody UpdateUserRequest newUser) throws UserNotFoundException;

    public void deleteUser();

    public PostPageResponse getUserPosts(@PathVariable Long id,
                                         @PageableDefault(size = 10, sort = {"post_time"}, direction = Sort.Direction.DESC)
                                                 Pageable pageable) throws UserNotFoundException;

    public ColleaguesPageResponse getUsersColleagues(@PathVariable Long id,
                                                     @PageableDefault(size = 10)
                                                             Pageable pageable) throws UserNotFoundException;

    public void sendColleagueInvite(@PathVariable Long id) throws UserNotFoundException, SentInviteException;

    public UserResponse covertUserToResponse(User user);

    public ColleaguesPageResponse convertUsersToPageResponse(ColleaguesPage page);

    public PostPageResponse convertPostsToPageResponse(PostPage page);


}
