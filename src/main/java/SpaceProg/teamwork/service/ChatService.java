package SpaceProg.teamwork.service;

import SpaceProg.teamwork.exeption.notFoundExeption.UserNotFoundException;
import SpaceProg.teamwork.model.Message;
import SpaceProg.teamwork.model.chat.ChatAbstract;
import SpaceProg.teamwork.model.chat.DirectChat;
import SpaceProg.teamwork.model.chat.GroupChat;
import SpaceProg.teamwork.model.page.MessagePage;
import SpaceProg.teamwork.payload.request.createRequest.CreateDirectChatRequest;
import SpaceProg.teamwork.payload.request.upgrateRequest.UpdateGroupChatRequest;
import SpaceProg.teamwork.payload.request.createRequest.CreateGroupChatRequest;
import SpaceProg.teamwork.payload.request.createRequest.MessageRequest;
import org.springframework.data.domain.Pageable;

public interface ChatService {

    ChatAbstract findById(Long id);

    GroupChat createGroupChat(CreateGroupChatRequest newGroupChat) throws UserNotFoundException;

    void saveChat(ChatAbstract chat);

    public void updateChat(ChatAbstract chat);

    public void deleteById(Long id);

    void updateGroupChat(Long id, UpdateGroupChatRequest users) throws Exception;

    Message sendGroupChatMessage(Long roomId, MessageRequest messageRequest) throws Exception;

    void deleteGroupChat(Long id);

    MessagePage getGroupChatMessages(Long id, Pageable pageable) throws Exception;

    Message sendDirectChatMessage(Long roomId, MessageRequest messageRequest) throws Exception;

    DirectChat createDirectChat(CreateDirectChatRequest newChat) throws UserNotFoundException;
}
