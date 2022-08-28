package SpaceProg.teamwork.controller.chat;

import SpaceProg.teamwork.exeption.notFoundExeption.UserNotFoundException;
import SpaceProg.teamwork.model.Message;
import SpaceProg.teamwork.model.chat.DirectChat;
import SpaceProg.teamwork.payload.request.createRequest.CreateDirectChatRequest;
import SpaceProg.teamwork.payload.request.createRequest.MessageRequest;
import SpaceProg.teamwork.payload.response.DirectChatResponse;
import SpaceProg.teamwork.payload.response.MessageResponse;
import SpaceProg.teamwork.service.ChatService;
import org.modelmapper.ModelMapper;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/directChat")
public class DirectChatControllerImpl {

    private final ChatService chatService;
    private final ModelMapper modelMapper;

    public DirectChatControllerImpl(ChatService chatService, ModelMapper modelMapper) {
        this.chatService = chatService;
        this.modelMapper = modelMapper;
    }

    @MessageMapping("/DCMessage.{roomId}")
    @SendTo("/topic/DCResponse.{roomId}")
    public MessageResponse sendGroupChatMessage(@DestinationVariable Long roomId, MessageRequest messageRequest) throws Exception {
        Message message = chatService.sendDirectChatMessage(roomId, messageRequest);

        return convertMessageToResponse(message);
    }

    @PostMapping()
    public DirectChatResponse createDirectChat (@RequestBody CreateDirectChatRequest newChat) throws UserNotFoundException {
        DirectChat chat = chatService.createDirectChat(newChat);

        return convertDirectChatToResponse(chat);

    }

    @DeleteMapping("/{id}")
    public void deleteDirectChat(@PathVariable Long id){
        chatService.deleteById(id);
    }

    private DirectChatResponse convertDirectChatToResponse(DirectChat chat) {
        return modelMapper.map(chat, DirectChatResponse.class);
    }


    public MessageResponse convertMessageToResponse(Message message) {
        return modelMapper.map(message, MessageResponse.class);
    }

}
