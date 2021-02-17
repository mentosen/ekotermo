package ekotermo.controller

import ekotermo.data.service.AccessCodeDataService
import ekotermo.dto.LoginRequestDto
import ekotermo.dto.RegistrationRequestDto
import ekotermo.model.CredentialViewModel
import ekotermo.service.AuthService
import ekotermo.service.security.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController {

    @Autowired private JwtTokenProvider jwtTokenProvider

    @Autowired private AuthService authService

    @PostMapping("/signin")
    CredentialViewModel loginUser(@RequestBody LoginRequestDto loginRequestDto) {

        return authService.login(loginRequestDto)
    }

    @PostMapping("/signup")
    CredentialViewModel registerUser(@RequestBody RegistrationRequestDto registrationRequestDto) {

        CredentialViewModel credentialViewModel = authService.singUp(registrationRequestDto)

        return credentialViewModel
    }

    @PostMapping("/signupcompany")
    CredentialViewModel registerCompany(@RequestBody RegistrationRequestDto registrationRequestDto) {

        CredentialViewModel credentialViewModel = authService.singUpCompany(registrationRequestDto)

        return credentialViewModel
    }
}
