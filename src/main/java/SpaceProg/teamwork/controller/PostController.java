package SpaceProg.teamwork.controller;

import SpaceProg.teamwork.model.Post;
import SpaceProg.teamwork.payload.request.createRequest.CreatePostRequest;
import SpaceProg.teamwork.payload.request.upgrateRequest.UpdatePostRequest;
import SpaceProg.teamwork.payload.response.PostResponse;

public interface PostController {


    PostResponse getPost(Long id);

    PostResponse createPost(CreatePostRequest post);

    PostResponse updatePost(UpdatePostRequest newPost, Long id);

    void deletePost(Long id) throws Exception;
}
