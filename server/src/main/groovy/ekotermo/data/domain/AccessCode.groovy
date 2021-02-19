package ekotermo.data.domain

import ekotermo.data.enums.AccessCodeStatus
import groovy.transform.ToString
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = 'accessCodes')
@ToString(includeFields = true, includeNames = true, includePackage = false)
class AccessCode extends BaseDomain{

    String code
    AccessCodeStatus status
    User user
}
