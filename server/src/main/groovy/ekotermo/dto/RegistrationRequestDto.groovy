package ekotermo.dto

import ekotermo.data.enums.Role
import groovy.transform.ToString

@ToString(includeNames = true, includePackage = false)
class RegistrationRequestDto {

    String login

    String password

    String city

    String street

    int building

    int apartment

    String phone

    String email

    String serialNumber

    List<Role> roles
}
