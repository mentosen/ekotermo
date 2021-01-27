package ekotermo.dto

import groovy.transform.ToString

@ToString(includeNames = true, includePackage = false)
class CheckDuplicateUserDto {

    CheckDuplicateUserDto() {}

    CheckDuplicateUserDto(String login, String email, String phone) {
        this.login = login
        this.phone = phone
        this.email = email
    }
    String login

    String phone

    String email
}
