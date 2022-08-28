package SpaceProg.teamwork.controller.chat;

import SpaceProg.teamwork.exeption.notFoundExeption.UserNotFoundException;
import SpaceProg.teamwork.model.Message;
import SpaceProg.teamwork.model.chat.GroupChat;
import SpaceProg.teamwork.model.page.MessagePage;
import SpaceProg.teamwork.payload.request.upgrateRequest.UpdateGroupChatRequest;
import SpaceProg.teamwork.payload.request.createRequest.CreateGroupChatRequest;
import SpaceProg.teamwork.payload.request.createRequest.MessageRequest;
import SpaceProg.teamwork.payload.response.GroupChatResponse;
import SpaceProg.teamwork.payload.response.MessageResponse;
import SpaceProg.teamwork.payload.response.pageResponse.MessagePageResponse;
import SpaceProg.teamwork.service.ChatService;
import SpaceProg.teamwork.service.MessageService;
import SpaceProg.teamwork.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/groupChat")
@Slf4j
class GroupChatControllerImpl {

    private final ChatService chatService;
    private final ModelMapper modelMapper;

    public GroupChatControllerImpl(ChatService chatService, ModelMapper modelMapper) {
        this.chatService = chatService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/chat/{id}")
    public MessagePageResponse getGroupChatMessages(@PathVariable Long id,
                                                    @PageableDefault(size = 10, sort = {"sending_time"}, direction = Sort.Direction.DESC)
                                           Pageable pageable) throws Exception {

        MessagePage messagePage = chatService.getGroupChatMessages(id, pageable);
        return convertMessagePageToResponse(messagePage);
    }

    @MessageMapping("/GCMessage.{roomId}")
    @SendTo("/topic/GCResponse.{roomId}")
    public MessageResponse sendGroupChatMessage(@DestinationVariable Long roomId, MessageRequest messageRequest) throws Exception {
        Message message = chatService.sendGroupChatMessage(roomId, messageRequest);

        return convertMessageToResponse(message);
    }

    @PostMapping()
    public GroupChatResponse createGroupChat(@RequestBody CreateGroupChatRequest newGroupChat) throws UserNotFoundException {
        GroupChat groupChat = chatService.createGroupChat(newGroupChat);
        return convertGroupChatToResponse(groupChat);
    }

    @PutMapping("newUsers/{id}")
    public void addUsersToGroupChat(@PathVariable Long id, @RequestBody UpdateGroupChatRequest users) throws Exception {
        chatService.updateGroupChat(id, users);
    }

    @DeleteMapping("/{id}")
    public void deleteGroupChat(@PathVariable Long id){
        chatService.deleteGroupChat(id);
    }
    //converters

    public MessageResponse convertMessageToResponse(Message message) {
        return modelMapper.map(message, MessageResponse.class);
    }

    private MessagePageResponse convertMessagePageToResponse(MessagePage messagePage) {
        return modelMapper.map(messagePage, MessagePageResponse.class);
    }

    private GroupChatResponse convertGroupChatToResponse(GroupChat groupChat){
        return modelMapper.map(groupChat, GroupChatResponse.class);
    }

}
