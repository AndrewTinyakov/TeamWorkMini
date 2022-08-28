package SpaceProg.teamwork.payload.response.pageResponse;

import SpaceProg.teamwork.payload.response.LinkToUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColleaguesPageResponse {
    List<LinkToUser> linkToUserList;
    private int currentPage;
    private int totalPage;
}
