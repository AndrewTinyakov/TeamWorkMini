package SpaceProg.teamwork.payload.request.colleagues;

import SpaceProg.teamwork.payload.response.LinkToUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColleaguesInviteRequest {
    Long id;

    LinkToUser sender;
}
