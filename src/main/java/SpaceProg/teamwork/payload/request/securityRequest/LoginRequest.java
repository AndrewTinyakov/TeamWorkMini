package SpaceProg.teamwork.payload.request.securityRequest;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class LoginRequest {
    @NotEmpty(message = "The login must not be empty")
    @Size(min = 2, max = 100, message = "Имя должно быть от 2 до 100 символов длиной")
    private String username;

    private String password;

}
