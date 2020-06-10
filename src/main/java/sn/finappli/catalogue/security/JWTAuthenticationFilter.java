package sn.finappli.catalogue.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import sn.finappli.catalogue.entities.UserTestPOJO;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        UserTestPOJO pojo;
        try {
            pojo = new ObjectMapper().readValue(request.getInputStream(), UserTestPOJO.class);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new AuthenticationServiceException(e.getMessage(), e);
        }
        var user = new UsernamePasswordAuthenticationToken(pojo.getUsername(), pojo.getPassword());
        return authenticationManager.authenticate(user);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) {
        log.info(authResult.getPrincipal());

        var user = (UserDetails) authResult.getPrincipal();
        log.info(user);
        var encodedSecret = SecurityConstant.SECRET.getBytes();

        var roles = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        String jwt = Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(encodedSecret), SignatureAlgorithm.HS512)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstant.EXPIRATION))
                .claim(SecurityConstant.CLAIM_NAME, roles)
                .compact();

        response.addHeader(SecurityConstant.HEADER_NAME, SecurityConstant.TOKEN_PREFIX + jwt);
    }
}

