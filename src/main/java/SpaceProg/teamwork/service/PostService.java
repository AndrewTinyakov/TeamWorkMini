package SpaceProg.teamwork.service;

import SpaceProg.teamwork.exeption.notFoundExeption.UserNotFoundException;
import SpaceProg.teamwork.model.Post;
import SpaceProg.teamwork.model.page.PostPage;
import SpaceProg.teamwork.payload.request.createRequest.CreatePostRequest;
import SpaceProg.teamwork.payload.request.upgrateRequest.UpdatePostRequest;
import org.springframework.data.domain.Pageable;

public interface PostService {
    Post findById(Long id);

    Post createPost(CreatePostRequest post);

    Post updatePost(UpdatePostRequest newPost, Long id);

    void deleteById(Long id) throws Exception;

    PostPage findUsersPost(Long id, Pageable pageable) throws UserNotFoundException;

    public PostPage findFeedPosts(Pageable pageable);

}
