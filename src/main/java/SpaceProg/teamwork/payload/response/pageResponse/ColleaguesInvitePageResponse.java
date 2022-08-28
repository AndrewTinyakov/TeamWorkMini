package SpaceProg.teamwork.payload.response.pageResponse;

import SpaceProg.teamwork.payload.response.ColleaguesInviteResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColleaguesInvitePageResponse {
    private List<ColleaguesInviteResponse> colleaguesInvites;
    private int currentPage;
    private int totalPage;
}
