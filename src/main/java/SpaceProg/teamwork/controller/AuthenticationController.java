package SpaceProg.teamwork.controller;

import SpaceProg.teamwork.payload.request.securityRequest.LoginRequest;
import SpaceProg.teamwork.payload.request.securityRequest.SingupRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface AuthenticationController {
    public ResponseEntity<?> registerUser(@Valid @RequestBody SingupRequest singupRequest);

    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest);

//    public boolean findUserToResetPassword(String login, String email);

}
