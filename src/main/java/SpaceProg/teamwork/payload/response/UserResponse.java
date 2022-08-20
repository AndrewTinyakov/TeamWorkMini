package SpaceProg.teamwork.payload.response;

import SpaceProg.teamwork.model.Post;
import SpaceProg.teamwork.model.User;
import SpaceProg.teamwork.payload.response.LinkToUser;
import SpaceProg.teamwork.payload.response.PostResponse;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;

    private String username;

    private String email;

    private String name;

    private String surname;
}
