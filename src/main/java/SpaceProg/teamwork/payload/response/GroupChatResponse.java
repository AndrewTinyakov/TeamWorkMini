package SpaceProg.teamwork.payload.response;

import SpaceProg.teamwork.model.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupChatResponse {
    private Long id;

    private String name;

    private List<MessageResponse> messageList;

    private Set<LinkToUser> userSetOfChat;

    private Set<LinkToUser> adminSetOfChat;

}
