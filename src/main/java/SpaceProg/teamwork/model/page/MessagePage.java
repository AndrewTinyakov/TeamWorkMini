package SpaceProg.teamwork.model.page;

import SpaceProg.teamwork.model.Message;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class MessagePage {
    List<Message> messages;
    private int currentPage;
    private int totalPage;
}
