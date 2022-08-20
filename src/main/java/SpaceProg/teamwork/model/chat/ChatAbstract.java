package SpaceProg.teamwork.model.chat;

import SpaceProg.teamwork.model.Message;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.InheritanceType.SINGLE_TABLE;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorValue("CA")
@Table(name = "chats")
public abstract class ChatAbstract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Long id;

    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "toChat")
    private List<Message> messageList;


    public ChatAbstract(String name, List<Message> messageList) {
        this.name = name;
        this.messageList = null;
    }
}

