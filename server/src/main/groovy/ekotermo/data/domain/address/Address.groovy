package ekotermo.data.domain.address

import ekotermo.data.domain.BaseDomain
import groovy.transform.ToString
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = 'address')
@ToString(includeFields = true, includeNames = true, includePackage = false)
class Address extends BaseDomain{
    List<String> regions
    List<String> cities
    List<String> streets
}
