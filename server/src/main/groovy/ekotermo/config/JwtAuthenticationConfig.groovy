package ekotermo.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "ekotermo.jwt")
class JwtAuthenticationConfig {

    String url

    String header

    String prefix

    int accessExpiration // default 24 hours

    int refreshExpiration // default 24 hours

    String secret
}
