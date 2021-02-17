package ekotermo.data.domain

import ekotermo.data.enums.AccessCodeStatus
import ekotermo.data.enums.CounterSerialNumberStatus
import groovy.transform.ToString
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = 'counterSerialNumbers')
@ToString(includeFields = true, includeNames = true, includePackage = false)
class CounterSerialNumber extends BaseDomain{

    String number
    CounterSerialNumberStatus status
    int userId
}
