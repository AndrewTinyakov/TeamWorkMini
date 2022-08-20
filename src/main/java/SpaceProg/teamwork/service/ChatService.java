package SpaceProg.teamwork.service;

import SpaceProg.teamwork.model.chat.ChatAbstract;
import SpaceProg.teamwork.model.chat.GroupChat;
import SpaceProg.teamwork.payload.request.createRequest.CreateGroupChatRequest;

public interface ChatService {

    ChatAbstract findById(Long id);

    GroupChat createGroupChat(CreateGroupChatRequest newGroupChat);

    void saveChat(ChatAbstract chat);

    public void updateChat(ChatAbstract chat);

    public void deleteById(Long id);
}
