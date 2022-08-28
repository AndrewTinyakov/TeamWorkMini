package SpaceProg.teamwork.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColleaguesInviteResponse {
    private Long id;
    LinkToUser sender;
}
