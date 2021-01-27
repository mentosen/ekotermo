package ekotermo.dto

import groovy.transform.ToString

import javax.validation.constraints.NotBlank

@ToString(includePackage = false, includeNames = true)
class LoginRequestDto {

    /**
     * Login
     */
    @NotBlank
    String login

    /**
     * Password
     */
    @NotBlank
    String password
}
