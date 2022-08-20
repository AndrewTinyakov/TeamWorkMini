package SpaceProg.teamwork.payload.request.securityRequest;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Slf4j
public class SingupRequest {

    @NotBlank
    @Size(min = 2, max = 25,
            message = "login must be at least 2 and no more than 25 characters ")
    private String username;

    @NotBlank
    @Size(min = 2, max = 25,
            message = "password must be at least 6 and no more than 30 characters ")
    private String password;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 2)
    private String name;

    @NotBlank
    @Size(min = 2)
    private String surname;

    private Set<String> roles;


    public SingupRequest(
            @NotBlank @Size(min = 2, max = 25, message = "login must be at least 2 and no more than 25 characters ") String login,
            @NotBlank @Size(min = 2, max = 25, message = "password must be at least 6 and no more than 30 characters ") String password,
            @NotBlank @Email String email,
            @NotBlank @Size(min = 2) String name,
            @NotBlank @Size(min = 2) String surname,
            Set<String> roles) {
        this.username = login;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.roles = roles;
    }
}
