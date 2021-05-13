package ekotermo.data.domain.address

import groovy.transform.ToString
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = 'regions')
@ToString(includeFields = true, includeNames = true, includePackage = false)
class Region {
    String name

    @DBRef(lazy = true)
    List<City> cities
}
