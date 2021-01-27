package ekotermo.dto

import groovy.transform.ToString

@ToString(includeNames = true, includePackage = false)
class CheckDuplicateUserResponseDto {

    Boolean isLoginExists
    Boolean isEmailExists
    Boolean isPhoneExists
}
