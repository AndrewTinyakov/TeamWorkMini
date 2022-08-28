package SpaceProg.teamwork.service;

import SpaceProg.teamwork.dao.ChatDao;
import SpaceProg.teamwork.exeption.notFoundExeption.UserNotFoundException;
import SpaceProg.teamwork.model.Message;
import SpaceProg.teamwork.model.User;
import SpaceProg.teamwork.model.chat.ChatAbstract;
import SpaceProg.teamwork.model.chat.DirectChat;
import SpaceProg.teamwork.model.chat.GroupChat;
import SpaceProg.teamwork.model.page.MessagePage;
import SpaceProg.teamwork.payload.request.createRequest.CreateDirectChatRequest;
import SpaceProg.teamwork.payload.request.createRequest.CreateGroupChatRequest;
import SpaceProg.teamwork.payload.request.createRequest.MessageRequest;
import SpaceProg.teamwork.payload.request.upgrateRequest.UpdateGroupChatRequest;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ChatServiceImpl implements ChatService {
    private final ChatDao dao;
    private final UserService userService;
    private final MessageService messageService;

    public ChatServiceImpl(ChatDao dao, UserService userService,@Lazy MessageService messageService) {
        this.dao = dao;
        this.userService = userService;
        this.messageService = messageService;
    }

    public ChatAbstract findById(Long id) {
        return dao.findById(id).orElse(null);
    }

    public void saveChat(ChatAbstract chat) {
        dao.save(chat);
    }

    public void updateChat(ChatAbstract chat) {
        dao.save(chat);
    }

    public void deleteById(Long id) {
        dao.deleteById(id);
    }


    public GroupChat createGroupChat(CreateGroupChatRequest newGroupChat) throws UserNotFoundException {


        Set<User> userSet = new HashSet<>();
        for (Long id : newGroupChat.getUserIdSetOfChat()) {
            userSet.add(userService.findById(id));
        }

        GroupChat groupChat = new GroupChat(newGroupChat, userService.getCurrentUser(), userSet);
        saveChat(groupChat);
        return groupChat;
    }


    @Override
    public void updateGroupChat(Long id, UpdateGroupChatRequest newChat) throws Exception {

        GroupChat chat = (GroupChat) this.findById(id);
        for (User user : chat.getAdminSetOfChat()) {
            if (userService.getCurrentUser().getId().equals(user.getId())) {
                updateGroupChatLogic(chat, newChat);

                this.saveChat(chat);
            }
        }
        throw new Exception("No accept");
    }

    private void updateGroupChatLogic(GroupChat chat, UpdateGroupChatRequest newChat) throws UserNotFoundException {

        if (!chat.getName().equals(newChat.getName())) {
            chat.setName(newChat.getName());
        }

        if (newChat.getUsersId() != null) {
            for (Long userId : newChat.getUsersId()) {
                chat.getUserSetOfChat().add(userService.findById(userId));
            }
        }

        if (newChat.getAdminsId() != null) {
            for (Long userId : newChat.getAdminsId()) {
                chat.getUserSetOfChat().add(userService.findById(userId));
            }
        }


    }


    @Override
    public Message sendGroupChatMessage(Long roomId, MessageRequest messageRequest) throws Exception {

        GroupChat chat = (GroupChat) this.findById(messageRequest.getChatId());
        for (User user : chat.getUserSetOfChat()) {
            if (userService.getCurrentUser().getId().equals(user.getId())) {

                Message message = new Message(
                        userService.getCurrentUser(),
                        chat,
                        messageRequest.getText()
                );
                return messageService.saveMessage(message);

            }
        }

        throw new Exception("No accept");
    }

    @Override
    public void deleteGroupChat(Long id) {
        GroupChat chat = (GroupChat) this.findById(id);
        for (User user : chat.getAdminSetOfChat()) {
            if (user.getId().equals(userService.getCurrentUser().getId())) {
                deleteById(id);
            }
        }
    }

    @Override
    public MessagePage getGroupChatMessages(Long id, Pageable pageable) throws Exception {

        GroupChat chat = (GroupChat) this.findById(id);
        for (User user : chat.getUserSetOfChat()) {
            if (userService.getCurrentUser().getId().equals(user.getId())) {
                throw new Exception("No accept");
            }
        }

        return messageService.findAllInChat(id, pageable);

    }

    @Override
    public DirectChat createDirectChat(CreateDirectChatRequest newChat) throws UserNotFoundException {
        User user1 = userService.findById(newChat.getUserId1());
        User user2 = userService.findById(newChat.getUserId2());

        DirectChat chat = new DirectChat(user1, user2);
        return dao.save(chat);
    }

    //-----

    @Override
    public Message sendDirectChatMessage(Long roomId, MessageRequest messageRequest) throws Exception {
        DirectChat chat = (DirectChat) this.findById(roomId);
        Long currentId = userService.getCurrentUser().getId();
        if (currentId.equals(chat.getUser1().getId()) || currentId.equals(chat.getUser2().getId())) {


            Message message = new Message(
                    userService.getCurrentUser(),
                    chat,
                    messageRequest.getText()
            );
            return messageService.saveMessage(message);
        }

        throw new Exception();
    }


}
