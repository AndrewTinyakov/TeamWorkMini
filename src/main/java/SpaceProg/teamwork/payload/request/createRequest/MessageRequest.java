package SpaceProg.teamwork.payload.request.createRequest;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageRequest {
    private String text;
    private Long chatId;
}
