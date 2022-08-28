package SpaceProg.teamwork.payload.request.upgrateRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateGroupChatRequest {

    private String name;

    private Set<Long> usersId;

    private Set<Long> adminsId;

}
