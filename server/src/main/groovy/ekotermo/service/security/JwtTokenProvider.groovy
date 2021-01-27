package ekotermo.service.security

import ekotermo.data.enums.Role
import ekotermo.exceptions.ExpirationJwtTokenException
import ekotermo.exceptions.JwtTokenNotFoundException
import ekotermo.service.security.model.TokenType
import groovy.util.logging.Slf4j
import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.joda.time.DateTime
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Slf4j
@Service
class JwtTokenProvider {

    @Value('${ekotermo.jwt.prefix}')
    private String prefix

    @Value('${ekotermo.jwt.secret}')
    private String secret

    @Value('${ekotermo.jwt.accessExpiration}')
    private int accessExpiration

    @Value('${ekotermo.jwt.refreshExpiration}')
    private int refreshExpiration

    /**
     * Создание токена
     * @param username
     * @param roles
     * @return
     */
    String createToken(String userId, List<Role> roles, TokenType tokenType) {

        Claims claims = Jwts.claims().setSubject(userId)
        claims.put("auth", roles)

        Date validity

        if (tokenType == TokenType.ACCESS) {
            validity = new DateTime().plusSeconds(accessExpiration).toDate()
        } else {
            validity = new DateTime().plusSeconds(refreshExpiration).toDate()
        }

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact()
    }

    /**
     * Получить токен с заголовка
     * @param req
     * @return
     */
    String resolveToken(String bearerToken) {

        if (bearerToken != null && bearerToken.startsWith(prefix)) {
            return bearerToken.substring(7, bearerToken.length())
        }

        throw new JwtTokenNotFoundException("Not valid format of refresh token")
    }

    /**
     * Валидация токена
     * @param token
     * @return
     */
    String validateTokenAndGetUserId(String bearerToken) throws SecurityException{
        try {

            String token = this.resolveToken(bearerToken)

            Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody()

            if (claims.getExpiration() < new Date()) {
                throw new ExpirationJwtTokenException("Incoming JWT token is expired!")
            }

            return claims.getSubject()

        } catch (JwtException | JwtTokenNotFoundException | ExpirationJwtTokenException ignore) {
            log.warn("Expired or invalid JWT token: $bearerToken")
            throw new SecurityException("Expired or invalid JWT token")
        }
    }
}
