package ekotermo.service

import ekotermo.data.domain.AccessCode
import ekotermo.data.domain.CounterSerialNumber
import ekotermo.data.domain.User
import ekotermo.data.enums.AccessCodeStatus
import ekotermo.data.enums.CounterSerialNumberStatus
import ekotermo.data.enums.Role
import ekotermo.data.service.AccessCodeDataService
import ekotermo.data.service.CounterSerialNumberDataService
import ekotermo.data.service.UserDataService
import ekotermo.dto.LoginRequestDto
import ekotermo.dto.RegistrationRequestDto
import ekotermo.model.CredentialViewModel
import ekotermo.service.security.JwtTokenProvider
import ekotermo.service.security.model.TokenType
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Slf4j
@Service
class AuthService {

    @Autowired private JwtTokenProvider jwtTokenProvider

    @Autowired private UserDataService userDataService
    @Autowired private AccessCodeDataService accessCodeDataService
    @Autowired private CounterSerialNumberDataService counterSerialNumberDataService

    @Autowired private UserService userService

    /**
     * Логин пользователя
     * @param dto
     * @return
     */
    CredentialViewModel login(LoginRequestDto dto) {

        log.info("Login user by name [$dto.login] and password [$dto.password]")

        User user = userDataService.findByUserName(dto.login)

        return generateTokensAndBuildResponse(user)
    }

    /**
     * Регистрация пользователя
     * @param dto
     * @return
     */
    CredentialViewModel singUp(RegistrationRequestDto dto) {

        log.info("Login user by name [$dto.login] and password [$dto.password]")

        CounterSerialNumber counterSerialNumber = counterSerialNumberDataService.findByNumberAndStatus(dto.serialNumber,
                CounterSerialNumberStatus.FREE)

        dto.roles = [Role.ROLE_USER]

        User user = userService.create(dto)

        counterSerialNumberDataService.makeDisabled(user, counterSerialNumber)

        return generateTokensAndBuildResponse(user)
    }

    /**
     * Регистрация компании
     * @param dto
     * @return
     */
    CredentialViewModel singUpCompany(RegistrationRequestDto dto) {

        log.info("Login user by name [$dto.login] and password [$dto.password]")

        AccessCode accessCode = accessCodeDataService.findByCodeAndStatus(dto.serialNumber, AccessCodeStatus.FREE)

        dto.roles = [Role.ROLE_COMPANY]

        User user = userService.create(dto)

        accessCodeDataService.makeDisabled(user, accessCode)

        return generateTokensAndBuildResponse(user)
    }

    /**
     * Обновление токена
     * @param request
     * @return
     */
    CredentialViewModel refreshToken(String bearerToken) throws SecurityException {

        String userId = jwtTokenProvider.validateTokenAndGetUserId(bearerToken)

        User user = userDataService.findById(userId)

        String accessToken = jwtTokenProvider.createToken(user.id, user.roles, TokenType.ACCESS)
        String refreshToken = jwtTokenProvider.createToken(user.id, user.roles, TokenType.REFRESH)

        return CredentialViewModel.instance(user, accessToken, refreshToken)
    }

    /**
     *
     * @param user
     * @return
     */
    CredentialViewModel generateTokensAndBuildResponse(User user) {

        String accessToken = jwtTokenProvider.createToken(user.id, user.roles, TokenType.ACCESS)
        String refreshToken = jwtTokenProvider.createToken(user.id, user.roles, TokenType.REFRESH)

        return CredentialViewModel.instance(user, accessToken, refreshToken)
    }
}
