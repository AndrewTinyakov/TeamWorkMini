package SpaceProg.teamwork.service;

import SpaceProg.teamwork.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WSService {
    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;

    @Autowired
    public WSService(SimpMessagingTemplate messagingTemplate, MessageService messageService) {
        this.messagingTemplate = messagingTemplate;
        this.messageService = messageService;
    }

    public void notifyUser(final Long id, final Message message){
        messageService.saveMessage(message);
        messagingTemplate.convertAndSendToUser(id.toString(),"/topic/direct-messages", message);
    }
}
