package school.core;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import school.service.AuthenticationService;
import school.dto.UserRolesDto;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class DemoAuthenticationFilter extends OncePerRequestFilter {
    private final AuthenticationService authenticationService;

    public DemoAuthenticationFilter(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if ( ! StringUtils.hasLength(authorizationHeader) || ! authorizationHeader.startsWith("Bearer ")) {
            throw new AuthenticationCredentialsNotFoundException("Authentication failed!" + request);
        }

        String token = tokenExtract(authorizationHeader);
        UserRolesDto userRoles = authenticationService.authenticate(token);

        List<SimpleGrantedAuthority> roles = userRoles.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userRoles.getUserName(),null, roles);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    private static String tokenExtract(String bearerToken) {
        return bearerToken.substring("Bearer".length()).trim();
    }

}
