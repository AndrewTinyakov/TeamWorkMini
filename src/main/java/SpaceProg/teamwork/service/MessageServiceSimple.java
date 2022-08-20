package SpaceProg.teamwork.service;

import SpaceProg.teamwork.dao.MessageDao;
import SpaceProg.teamwork.model.Message;
import SpaceProg.teamwork.model.chat.ChatAbstract;
import SpaceProg.teamwork.model.page.MessagePage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MessageServiceSimple implements MessageService {
    private final MessageDao dao;
    private final ChatServiceImpl chatServiceImpl;


    public MessageServiceSimple(MessageDao dao, ChatServiceImpl chatServiceImpl) {
        this.dao = dao;
        this.chatServiceImpl = chatServiceImpl;
    }

    @Override
    public Message findById(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public Message saveMessage(Message message) {
        return dao.save(message);
    }

    @Override
    public Message updateMessage(String newMessage, Long id) {
        Message message = this.findById(id);
        message.setText(newMessage);
        return message;
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }

    @Override
    public MessagePage findAllInChat(Long id, Pageable pageable) {
        ChatAbstract chat = chatServiceImpl.findById(id);
        List<Message> messageList =  chat.getMessageList();
        Page<Message> page = new PageImpl<>(messageList);

        return new MessagePage(
                page.getContent(),
                pageable.getPageNumber(),
                page.getTotalPages()
        );
    }
}
