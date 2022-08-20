package SpaceProg.teamwork.controller;

import SpaceProg.teamwork.model.Message;
import SpaceProg.teamwork.model.chat.GroupChat;
import SpaceProg.teamwork.payload.request.createRequest.CreateGroupChatRequest;
import SpaceProg.teamwork.model.page.MessagePage;
import SpaceProg.teamwork.service.ChatService;
import SpaceProg.teamwork.service.MessageService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;


@RestController
@RequestMapping("/api")
class ChatController {
    private final MessageService messageService;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatService chatService;

    public ChatController(MessageService messageService, SimpMessagingTemplate simpMessagingTemplate, ChatService chatService) {
        this.messageService = messageService;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.chatService = chatService;
    }

    @GetMapping("/chat/{id}")
    public MessagePage getMessages(@PathVariable Long id,
                                   @PageableDefault(size = 10, sort = {"sending_time"}, direction = Sort.Direction.DESC)
                                                   Pageable pageable) {

        return messageService.findAllInChat(id, pageable);
        //TODO
    }

    @PostMapping()
    public GroupChat createGroupChat (@RequestBody CreateGroupChatRequest newGroupChat) {
        return chatService.createGroupChat(newGroupChat);
    }



}
