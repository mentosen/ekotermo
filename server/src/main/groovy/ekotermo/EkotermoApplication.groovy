package ekotermo

import ekotermo.config.JwtAuthenticationConfig
import ekotermo.data.domain.AccessCode
import ekotermo.data.domain.CounterSerialNumber
import ekotermo.data.enums.AccessCodeStatus
import ekotermo.data.enums.CounterSerialNumberStatus
import ekotermo.data.service.AccessCodeDataService
import ekotermo.data.service.CounterSerialNumberDataService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.data.mongodb.config.EnableMongoAuditing

@Slf4j
@EnableMongoAuditing
@SpringBootApplication(exclude = [SecurityAutoConfiguration.class])
@EnableConfigurationProperties(JwtAuthenticationConfig.class)
class EkotermoApplication {

    static void main(String[] args) {
        SpringApplication.run EkotermoApplication, args

        log.info("\n\n-----------------------------------------------------------------\n\n" +
                "\t\t*********************************************************\n" +
                "\t\t**********!!!! SERVER STARTED SUCCESSFULLY !!!!**********\n" +
                "\t\t*********************************************************\n" +
                "\n-----------------------------------------------------------------\n\n")
    }
}
