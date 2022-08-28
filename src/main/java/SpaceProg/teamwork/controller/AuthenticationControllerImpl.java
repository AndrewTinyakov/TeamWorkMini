package SpaceProg.teamwork.controller;

import SpaceProg.teamwork.dao.RoleDao;
import SpaceProg.teamwork.payload.request.securityRequest.LoginRequest;
import SpaceProg.teamwork.payload.response.securityResponse.JwtResponse;
import SpaceProg.teamwork.payload.request.securityRequest.SingupRequest;
import SpaceProg.teamwork.model.ERole;
import SpaceProg.teamwork.model.Role;
import SpaceProg.teamwork.model.User;
import SpaceProg.teamwork.security.jwt.JwtUtils;
import SpaceProg.teamwork.security.service.UserDetailsImpl;
import SpaceProg.teamwork.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@CrossOrigin()
class AuthenticationControllerImpl implements AuthenticationController {

    AuthenticationManager authenticationManager;
    UserService userService;
    RoleDao roleRepository;
    PasswordEncoder encoder;
    JwtUtils jwtUtils;

    public AuthenticationControllerImpl(AuthenticationManager authenticationManager, UserService userService, RoleDao roleRepository, PasswordEncoder encoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SingupRequest singupRequest) {
        if (userService.existsByUsername(singupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken!");
        }

        if (userService.existsByEmail(singupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email is already in use!");
        }

        // Create new user's account
        User user = new User(singupRequest);

        Set<String> strRoles = singupRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userService.register(user);

        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }



//    @PostMapping("/forgotPassword")
//    public boolean findUserToResetPassword(@RequestParam String login, String email) {
//        return service.findUserToResetPassword(login, email);
//    }

//    @PostMapping("/forgotPassword/reset")
//    public void resetPassword(String login, String newPassword) {
//        service.resetPassword(login, newPassword);
//    }



}
