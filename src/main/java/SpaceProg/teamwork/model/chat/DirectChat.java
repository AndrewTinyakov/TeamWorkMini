package SpaceProg.teamwork.model.chat;

import SpaceProg.teamwork.model.Message;
import SpaceProg.teamwork.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.List;

@NoArgsConstructor
@Entity
@Data
public class DirectChat extends ChatAbstract {

    @OneToOne()
    @JoinColumn(name = "user_id1")
    private User user1;

    @OneToOne
    @JoinColumn(name = "user_id2")
    private User user2;

    public DirectChat(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
    }
}
