package SpaceProg.teamwork.controller;


import SpaceProg.teamwork.model.page.PostPage;
import SpaceProg.teamwork.payload.response.pageResponse.PostPageResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;

public interface FeedController {

    PostPageResponse feed(
            @PageableDefault(size = 10, sort = {"post_time"}, direction = Sort.Direction.DESC) Pageable pageable);

}
