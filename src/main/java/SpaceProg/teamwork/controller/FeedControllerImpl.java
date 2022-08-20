package SpaceProg.teamwork.controller;


import SpaceProg.teamwork.model.page.PostPage;
import SpaceProg.teamwork.payload.response.pageResponse.PostPageResponse;
import SpaceProg.teamwork.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/feed")
class FeedControllerImpl implements FeedController {
    private final PostService postService;
    private final ModelMapper modelMapper;

    @Autowired
    public FeedControllerImpl(PostService postService, ModelMapper modelMapper) {
        this.postService = postService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public PostPageResponse feed(
            @PageableDefault(size = 10, sort = {"post_time"}, direction = Sort.Direction.DESC) Pageable pageable) {
        PostPage page = postService.findFeedPosts(pageable);
        return convertPostsToPageResponse(page);
    }

    public PostPageResponse convertPostsToPageResponse(PostPage page) {
        return modelMapper.map(page, PostPageResponse.class);
    }


}
