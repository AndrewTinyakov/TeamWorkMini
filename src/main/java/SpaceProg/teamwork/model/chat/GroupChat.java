package SpaceProg.teamwork.model.chat;

import SpaceProg.teamwork.model.User;
import SpaceProg.teamwork.payload.request.createRequest.CreateGroupChatRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
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

    @ManyToMany
    @JoinTable(
            name ="admins_GC",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "chat_id")
    )
    private Set<User> adminSetOfChat;

    public GroupChat(CreateGroupChatRequest newChat, User currentUser, Set<User> userSet) {
        super(newChat.getName(), null);
        this.userSetOfChat = userSet;
        this.adminSetOfChat = new HashSet<>();
        this.adminSetOfChat.add(currentUser);
    }
}
