package ekotermo.config

import ekotermo.data.domain.User
import ekotermo.service.security.model.UserPrincipal
import groovy.util.logging.Slf4j
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Slf4j
class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    private final JwtAuthenticationConfig config

    JwtTokenAuthenticationFilter(JwtAuthenticationConfig config) {
        this.config = config
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse rsp, FilterChain filterChain) throws ServletException, IOException {

        String token = req.getHeader(config.header)

        if (token != null && token.startsWith(config.prefix + " ")) {

            token = token.replace(config.prefix + " ", "")

            try {

                Claims claims = Jwts.parser()
                        .setSigningKey(config.secret)
                        .parseClaimsJws(token)
                        .getBody()

                String userId = claims.getSubject()

                if (userId) {

                    List<String> authorities = claims.get("auth", List.class)

                    List<SimpleGrantedAuthority> grantedAuthorities = authorities.collect {
                        new SimpleGrantedAuthority(it)
                    }

                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(UserPrincipal.instance(userId, grantedAuthorities), null, grantedAuthorities)

                    SecurityContextHolder.getContext().setAuthentication(auth)
                }

            } catch (Exception e) {

                log.warn(e.message)

                SecurityContextHolder.clearContext()

                rsp.setStatus(HttpStatus.UNAUTHORIZED.value())
                rsp.getWriter().write(e.message)

                return
            }

        } else if (req.pathInfo && req.pathInfo.contains('private') && token == null) {

            log.error("Not permitted request! Path:[${req.pathInfo}]")

            SecurityContextHolder.clearContext()

            rsp.setStatus(HttpStatus.UNAUTHORIZED.value())
            rsp.getWriter().write("Not authorized request by path:[${req.pathInfo}]")

            return
        }

        filterChain.doFilter(req, rsp)
    }
}

