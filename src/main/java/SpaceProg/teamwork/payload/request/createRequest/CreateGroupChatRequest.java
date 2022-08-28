package SpaceProg.teamwork.payload.request.createRequest;

import SpaceProg.teamwork.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class CreateGroupChatRequest {
    String name;

    Set<Long> userIdSetOfChat;
}
