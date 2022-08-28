package SpaceProg.teamwork.model.page;

import SpaceProg.teamwork.model.ColleaguesInvite;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColleaguesInvitePage {
    private List<ColleaguesInvite> colleaguesInvites;
    private int currentPage;
    private int totalPage;
}
