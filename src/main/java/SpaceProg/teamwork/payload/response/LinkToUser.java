package SpaceProg.teamwork.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkToUser {

    private Long id;

    private String username;

    private MultipartFile avatar;

}
