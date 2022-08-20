package SpaceProg.teamwork.model.chat;

import SpaceProg.teamwork.model.User;
import SpaceProg.teamwork.payload.request.createRequest.CreateGroupChatRequest;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;


@NoArgsConstructor
@Entity
@DiscriminatorValue("GC")
public class GroupChat extends ChatAbstract {

    @ManyToMany
    @JoinTable(
            name ="users_GC",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "chat_id")
    )
    private Set<User> userSetOfChat;

    public GroupChat(CreateGroupChatRequest newChat) {
        super(newChat.getName(), null);
        this.userSetOfChat = newChat.getUserSetOfChat();
    }
}
