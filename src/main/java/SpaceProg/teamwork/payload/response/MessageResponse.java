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

    private Set<Long> usersIdCheckedMessages;

    private Date sendingTime;
}
