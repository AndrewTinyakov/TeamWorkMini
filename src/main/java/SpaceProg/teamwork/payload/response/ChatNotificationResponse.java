package SpaceProg.teamwork.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatNotificationResponse {

    //TODO link to chat?
    private LinkToUser linkToUser;

    private String message;
}
