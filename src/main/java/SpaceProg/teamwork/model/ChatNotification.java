package SpaceProg.teamwork.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class ChatNotification {
    private Long id;
    private User user;
    private Message message;
}
