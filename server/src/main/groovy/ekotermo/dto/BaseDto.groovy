package ekotermo.dto

import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.ToString

@ToString(includeFields = true, includeNames = true, includePackage = false)
class BaseDto {

    String id
}
