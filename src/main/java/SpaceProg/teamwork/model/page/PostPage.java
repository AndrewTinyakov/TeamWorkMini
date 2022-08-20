package SpaceProg.teamwork.model.page;

import SpaceProg.teamwork.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostPage {
    private List<Post> posts;
    private int currentPage;
    private int totalPage;
}
