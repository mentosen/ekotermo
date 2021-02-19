package ekotermo.dto

import groovy.transform.ToString

@ToString(includeNames = true, includePackage = false)
class CodeResponseDto {

    Boolean isAccessCodeCorrect
    Boolean isCounterSerialNumberCorrect
}
