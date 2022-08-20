package SpaceProg.teamwork.payload.response.pageResponse;

import SpaceProg.teamwork.model.Message;
import SpaceProg.teamwork.payload.response.MessageResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessagePageResponse {
    List<MessageResponse> messages;
    private int currentPage;
    private int totalPage;
}
