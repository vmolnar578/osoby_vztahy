package school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import school.dto.UserRolesDto;
import school.service.AuthenticationService;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Optional;

@RestController
public class AuthenticationController {
    private static final int TOKEN_VALIDITY_IN_MINUTES = 15;
    private final String AUTHORIZATION_HEADER = "Authorization";
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/api/authentication")
    public void login(@RequestHeader(value = AUTHORIZATION_HEADER, required = false) Optional<String> authentication,
                      HttpServletResponse response) {
        if (authentication.isEmpty()) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
        }

        String[] credentials = credentialsDecode(authentication.get());

        String token = authenticationService.authenticate(credentials[0], credentials[1]);
        Long tokenExpiration = LocalDateTime.now().plus(TOKEN_VALIDITY_IN_MINUTES, ChronoUnit.MINUTES)
                .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        response.setStatus(HttpStatus.OK.value());
        response.addHeader("Access-Control-Expose-Headers", AUTHORIZATION_HEADER);
        response.addHeader(AUTHORIZATION_HEADER, "Bearer " + token);

        response.addHeader("Access-Control-Expose-Headers", "Expiration");
        response.addHeader("Expiration", String.valueOf(tokenExpiration));
    }

    private static String[] credentialsDecode(String authorization) {
        String base64Credentials = authorization.substring("Basic".length()).trim();
        byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
        String credentials = new String(credDecoded, StandardCharsets.UTF_8);

        return  credentials.split(":", 2);
    }

    @GetMapping("/api/authentication")
    public UserRolesDto checkToken(@RequestHeader(value = AUTHORIZATION_HEADER, required = true) String authentication,
                      HttpServletResponse response) {
        if (authentication.isEmpty()) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
        }
        String token = authentication.substring("Bearer".length()).trim();
        UserRolesDto userRolesDto = authenticationService.authenticate(token);
        response.setStatus(HttpStatus.OK.value());
        return userRolesDto;
    }

    @DeleteMapping("/api/authentication")
    public void logoff(@RequestHeader(value = AUTHORIZATION_HEADER, required = true) Optional<String> authentication) {
        String token = authentication.get().substring("Bearer".length()).trim();
        authenticationService.tokenRemove(token);
    }
}
