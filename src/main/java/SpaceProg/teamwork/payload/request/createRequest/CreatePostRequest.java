package SpaceProg.teamwork.payload.request.createRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostRequest {

    private String header;

    private String text;

}
