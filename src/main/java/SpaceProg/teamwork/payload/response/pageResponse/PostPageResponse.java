package SpaceProg.teamwork.payload.response.pageResponse;

import SpaceProg.teamwork.model.Post;
import SpaceProg.teamwork.payload.response.PostResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostPageResponse {
    private List<PostResponse> posts;
    private int currentPage;
    private int totalPage;
}
