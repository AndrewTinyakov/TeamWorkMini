package SpaceProg.teamwork.model;

import SpaceProg.teamwork.model.chat.ChatAbstract;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor

@Entity
@Table(name = "messages")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "message_id", nullable = false, unique = true, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private ChatAbstract toChat;

    @Column(name = "text")
    private String text;

    @Column(name = "fixed")
    private boolean fixed;

    @Column(name = "sending_time" , nullable = false, updatable = false)
    @CreatedDate
    private Date sendingTime;

    public Message(User user, ChatAbstract toChat, String text) {
        this.user = user;
        this.toChat = toChat;
        this.text = text;
        this.fixed = false;
        this.sendingTime = new Date();
    }
}
