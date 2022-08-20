package SpaceProg.teamwork.service;

import SpaceProg.teamwork.dao.ChatDao;
import SpaceProg.teamwork.model.chat.ChatAbstract;
import SpaceProg.teamwork.model.chat.GroupChat;
import SpaceProg.teamwork.payload.request.createRequest.CreateGroupChatRequest;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {
    private final ChatDao dao;

    public ChatServiceImpl(ChatDao dao) {
        this.dao = dao;
    }


    public ChatAbstract findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public void saveChat(ChatAbstract chat) {
        dao.save(chat);
    }

    public GroupChat createGroupChat(CreateGroupChatRequest newGroupChat) {
        GroupChat groupChat = new GroupChat(newGroupChat);
        saveChat(groupChat);
        return groupChat;
    }

    public void updateChat(ChatAbstract chat) {
        dao.save(chat);
    }


    public void deleteById(Long id) {
        dao.deleteById(id);
    }
}
