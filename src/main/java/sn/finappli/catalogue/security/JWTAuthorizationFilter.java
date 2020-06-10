package sn.finappli.catalogue.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {
        setHeaders(response);
        var authentication = getAuthentication(request);
        if (authentication == null) {
            filterChain.doFilter(request, response);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private void setHeaders(@NotNull HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method," +
                "Access-Control-Request-Headers, Authorization");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, OPTIONS");
    }

    private @Nullable UsernamePasswordAuthenticationToken getAuthentication(@NotNull HttpServletRequest request) {
        var token = request.getHeader(SecurityConstant.HEADER_NAME);
        if (StringUtils.isNotEmpty(token) && token.startsWith(SecurityConstant.TOKEN_PREFIX)) {
            try {
                var parsedToken = Jwts.parserBuilder()
                        .setSigningKey(SecurityConstant.SECRET.getBytes()).build()
                        .parseClaimsJws(token.replace(SecurityConstant.TOKEN_PREFIX, ""));

                var authorities = ((List<?>) parsedToken.getBody()
                        .get(SecurityConstant.CLAIM_NAME))
                        .stream()
                        .map(authority -> new SimpleGrantedAuthority(authority.toString()))
                        .collect(Collectors.toList());

                var username = parsedToken.getBody().getSubject();
                if (StringUtils.isNotEmpty(username)) return new UsernamePasswordAuthenticationToken(username, null, authorities);
            } catch (ExpiredJwtException exception) {
                log.warn("Request to parse expired JWT : {} failed : {}", token, exception.getMessage());
            } catch (UnsupportedJwtException exception) {
                log.warn("Request to parse unsupported JWT : {} failed : {}", token, exception.getMessage());
            } catch (MalformedJwtException exception) {
                log.warn("Request to parse invalid JWT : {} failed : {}", token, exception.getMessage());
            } catch (SignatureException exception) {
                log.warn("Request to parse JWT with invalid signature : {} failed : {}", token, exception.getMessage());
            } catch (IllegalArgumentException exception) {
                log.warn("Request to parse empty or null JWT : {} failed : {}", token, exception.getMessage());
            }
        }
        return null;
    }

}
