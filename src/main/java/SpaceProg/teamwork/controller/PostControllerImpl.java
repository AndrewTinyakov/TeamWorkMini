package SpaceProg.teamwork.controller;

import SpaceProg.teamwork.model.Post;
import SpaceProg.teamwork.payload.request.createRequest.CreatePostRequest;
import SpaceProg.teamwork.payload.request.upgrateRequest.UpdatePostRequest;
import SpaceProg.teamwork.payload.response.PostResponse;
import SpaceProg.teamwork.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
class PostControllerImpl implements PostController {

    private final PostService postService;
    private final ModelMapper modelMapper;

    public PostControllerImpl(PostService postService, ModelMapper modelMapper) {
        this.postService = postService;
        this.modelMapper = modelMapper;
    }

    @Override
    @GetMapping("/{id}")
    public PostResponse getPost(@PathVariable Long id) {
        Post post = postService.findById(id);
        return convertPostToResponse(post);
    }

    @Override
    @PostMapping()
    public PostResponse createPost(@RequestBody CreatePostRequest postRequest) {
        Post post = postService.createPost(postRequest);
        return convertPostToResponse(post);
    }
    //TODO post mapping with request class

    @Override
    @PutMapping("/{id}")
    public PostResponse updatePost(@RequestBody UpdatePostRequest newPost, @PathVariable Long id) {
        Post post = postService.updatePost(newPost, id);
        return convertPostToResponse(post);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) throws Exception {
        postService.deleteById(id);
    }



    private PostResponse convertPostToResponse(Post post) {
        return modelMapper.map(post, PostResponse.class);
    }
}