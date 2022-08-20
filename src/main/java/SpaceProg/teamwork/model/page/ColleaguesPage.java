package SpaceProg.teamwork.model.page;

import SpaceProg.teamwork.model.User;
import SpaceProg.teamwork.payload.response.LinkToUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ColleaguesPage {
    List<User> linkToUserList;
    private int currentPage;
    private int totalPage;

}
