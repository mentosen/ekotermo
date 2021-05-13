package ekotermo.data.domain.address

import ekotermo.data.domain.BaseDomain
import groovy.transform.ToString
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = 'cities')
@ToString(includeFields = true, includeNames = true, includePackage = false)
class City extends BaseDomain{
    String name
    List<String> streets
}
