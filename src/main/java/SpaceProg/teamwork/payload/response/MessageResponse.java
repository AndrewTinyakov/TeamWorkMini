package SpaceProg.teamwork.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {

    private Long id;

    private LinkToUser linkToUser;

    private Long chatId;

    private String text;

    private boolean fixed;

    private Set<LinkToUser> usersCheckedMessages;

    private Date sendingTime;

    public MessageResponse(LinkToUser linkToUser, Long chatId, String text, boolean fixed, Set<LinkToUser> usersCheckedMessages, Date sendingTime) {
        this.linkToUser = linkToUser;
        this.chatId = chatId;
        this.text = text;
        this.fixed = fixed;
        this.usersCheckedMessages = usersCheckedMessages;
        this.sendingTime = sendingTime;
    }
}
