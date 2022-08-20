package SpaceProg.teamwork.service;

import SpaceProg.teamwork.model.Message;
import SpaceProg.teamwork.model.page.MessagePage;
import org.springframework.data.domain.Pageable;

public interface MessageService {
    Message findById(Long id);

    Message saveMessage(Message message);

    Message updateMessage(String newMessage, Long id);

    void deleteById(Long id);

    MessagePage findAllInChat(Long id, Pageable pageable);


}
