package SpaceProg.teamwork.payload.request.createRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDirectChatRequest {

    private Long userId1;

    private Long userId2;
}
